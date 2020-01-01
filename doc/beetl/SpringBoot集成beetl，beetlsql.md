## 1.使用IDEA新建一个项目

> 本文使用`IDEA`的`Spring Initializr`新建项目，`eclipse`用户可以使用maven或者通过`STS`插件新建

![Spring Initializr](https://img-blog.csdnimg.cn/20190309212444662.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21raWlfaG9uZw==,size_16,color_FFFFFF,t_70)
## 2.引入依赖
> `HikariCp`：这里使用的数据库连接池
> `beetl`：模板引擎的jar，负责页面渲染
> `beetlsql`：负责数据库操作
> `Beetl官网`：http://ibeetl.com/
```
<!-- Beetl视图模板引擎 -->
<dependency>
    <groupId>com.ibeetl</groupId>
    <artifactId>beetl</artifactId>
    <version>2.8.1</version>
</dependency>
<!-- beetlsql jar -->
<dependency>
    <groupId>com.ibeetl</groupId>
    <artifactId>beetlsql</artifactId>
    <version>2.7.5</version>
</dependency>
<!-- HikariCp 数据库连接池 -->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>2.6.1</version>
</dependency>
<!-- 这里如果用mysql 8.0版本会报错，连接到到别的数据库，原因不详 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.36</version>
    <scope>runtime</scope>
</dependency>
```
## 3.集成Beetl模板引擎
### a.在/resources/下新建beetl.properties文件
```
TEMPLATE_CHARSET=UTF-8
# 配置模板引擎,如不配置可能会导致页面再次访问时报错
ENGINE=org.beetl.core.engine.DefaultTemplateEngine
# 配置beetl的定界符和占位符
DELIMITER_PLACEHOLDER_START=${
DELIMITER_PLACEHOLDER_END=}
DELIMITER_STATEMENT_START=@
DELIMITER_STATEMENT_END=
```
### b.负责页面模板引擎的配置类BeetlConf
```
/**
 * <p>beetl模板的配置类
 * 来源：https://www.jianshu.com/p/6285176ffc6c
 * </p>
 * @author mkii
 * @// TODO: 2019/3/4
 */
@Configuration
public class BeetlConf {
    @Value("templates") String templatesPath;  //模板根目录 ，比如 "templates"
    @Bean(name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //获取Spring Boot 的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader==null){
            loader = BeetlConf.class.getClassLoader();
        }
        //beetlGroupUtilConfiguration.setConfigProperties(extProperties);//额外的配置，可以覆盖默认配置，一般不需要
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader,
                templatesPath);
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
        return beetlGroupUtilConfiguration;
    }
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        beetlSpringViewResolver.setSuffix(".html");      //设置后缀后即可在controller中使用 return "/hello" 而不必 return "hello.html"
        return beetlSpringViewResolver;
    }
}
```
### c.测试页面hello.html
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>beetl test</title>
</head>
<body>
@ var a = 1;
<p>a=${a}</p>
</body>
</html>
```
### d.测试controller
```
@Controller
@RequestMapping("/Hello")
public class HelloController {
    @RequestMapping("/beetl.html")
    public String beetlTest(ModelMap modelMap) {
        return "hello";  //这里写页面的名称
    }
```
> 访问http://localhost:8080/zhimi/Hello/beetl.html 出现a=1就成功了。
## 3.集成BeetlSQL(使用`HikariCp`)
### a.配置数据库连接池信息`application.properties`
```
# 数据库配置
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/recruit?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Mkii1234
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
### b.beetlsql配置类
> `sqlManager`：核心类，负责主要的数据库操作
```
@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource")
    public DataSource dataSource(Environment environment) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        hikariDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        hikariDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        return hikariDataSource;
    }
    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource) {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }
    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("dataSource") DataSource datasource) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        factory.setNc(new UnderlinedNameConversion());//开启驼峰
        factory.setSqlLoader(new ClasspathLoader("/sql"));//sql文件路径
        return factory;
    }
    @Bean(name="sqlManager")
    public SQLManager getSqlManager(@Qualifier("sqlManagerFactoryBean") SqlManagerFactoryBean sqlManagerFactoryBean){
        SQLManager sqlManager = null;
        try {
            sqlManager = sqlManagerFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sqlManager;
    }
}
```
### c.新建文件POJO类UserLogin
```
public class UserLogin {
    private Integer id;
    private String userName;
    private String password;
    private String mobile;
    public UserLogin(){}
    // 省略getter()/setter()
```
### d.数据库表信息
```
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` unsigned int NOT NULL AUTO_INCREMENT COMMENT '登录用户id',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `mobile` char(11) NOT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_mobile` (`mobile`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO `user_login` VALUES('1','mkii','mkii1024','13192269632');
```

### e.在/resources/下新建sql/userLogin.md文件
```
selectSampleById
===
* 通过id查询
select * from user_login where 1=1
@if(!isEmpty(id)){
and id=#id#
@}
```
### f.测试controller
```
@Controller
@RequestMapping("/Hello")
public class HelloController {
    @Autowired SQLManager sqlManager;
    @RequestMapping("/beetlsql")
    @ResponseBody
    public UserLogin beetlsqlTest() {
        UserLogin userLogin = sqlManager.single(UserLogin.class, 1);
        return userLogin;
    }

    @RequestMapping("/userLoginList")
    @ResponseBody
    public List<UserLogin> beetlSqlList(){
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        List<UserLogin> userLoginList = sqlManager.select("userLogin.selectSampleById", UserLogin.class, userLogin);
        return userLoginList;
    }
}
```
> 测试`sqlManager.single()`
> 访问：http://localhost:8080/zhimi/Hello/beetlsql 出现 
> 结果：`{"id":1,"userName":"mkii","password":"mkii1024","mobile":"13192269632"}` 

>测试`sqlManager.select()`
>访问：http://localhost:8080/zhimi/Hello/userLoginList
>结果：`[{"id":1,"userName":"mkii","password":"mkii1024","mobile":"13192269632"}]`

## 4.项目源码
[源码下载](https://download.csdn.net/download/mkii_hong/11009070)