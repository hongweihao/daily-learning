最近在整合`velocity`模板引擎写的前端页面，遇到的一些小坑，记录一波：

#### 一、`velocity`是什么？

> 官方网站：http://velocity.apache.org/

> 官方文档：http://velocity.apache.org/engine/devel/user-guide.html#The_Mud_Store_Example

> 中文翻译文档：http://ifeve.com/apache-velocity-dev/

#### 二、什么是`springMVC`？

> 这个自己体会

#### 三、正式开始整合

##### 1、导入依赖
```
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context-support</artifactId>
  <version>${spring.version}</version>
</dependency>

<dependency>
  <groupId>org.apache.velocity</groupId>
  <artifactId>velocity</artifactId>
  <version>1.7</version>
</dependency>
<dependency>
  <groupId>org.apache.velocity</groupId>
  <artifactId>velocity-tools</artifactId>
  <version>2.0</version>
</dependency>
```
##### 2、在`web.xml`中配置`springMVC`
```
<!-- 配置DispatcherServlet -->
  <servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 配置springMVC需要加载的配置文件-->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
</servlet>
```
##### 3、在`spring-mvc.xml`中配置`velocity`
```
<!--velocity-->
<bean id="velocityConfig" 
      class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
    <!--这里配置vm文件的位置-->
    <property name="resourceLoaderPath" value="WEB-INF/templates"/>
    <!--这里配置velocity的其他属性，比如输入输出字符-->
    <property name="configLocation" value="classpath:velocity.properties"/>
</bean>

<bean id="viewResolver"
      class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">
    <property name="cache" value="false"/>
    <property name="prefix" value="" />
    <property name="suffix" value=".vm"/>
</bean>
```
##### 4、velocity.properties（这里只配置简单的编码，其他属性再另行配置）

> 这里只配置简单的编码
```
input.encoding=UTF-8
output.encoding=UTF-8
```
##### 5、然后编写个controller去调用模板代码吧，这里就不给出来了。

> 注意：`WEN-INF`下面的文件客户端不能直接引用，需要经过服务端去分发跳转。

#### 四、`velocity`的简单用法

> 前后端共享基于`context`容器，可以在`java`代码中往里面存值，然后在`vm`文件中获取，这是`velocity`的基本用法。

###### 1、变量：使用`$`声明变量，还可以用`$`取出`velocityContext`中的值。
```
##声明变量

#set($name = "hello") 
##引用变量

$name    ##不推荐
${name}  ##推荐

$!name    ##不推荐
$!{name}  ##推荐
```
> 注意：`${var}`获取变量时，若变量为空则原样输出。  
`$!{var}`获取变量时，若变量为空则变成空白输出。

##### 2、循环

```
#foreach ( $item in $list )
    ---
    $item           ##每一项
    $velocityCount  ##当前次数
    ---
#end
```
##### 3、条件控制
```
#if (condition)
    ---
#elseif(condition)
    ---
#else
    ---
#end 
```
##### 4、宏，可以理解为函数
```
##声明宏
#macro(sayHello $name)
    hello $name
#end

##使用宏
#sayHello("nick")
```
##### 5、parse和include
```
#parse("header.vm")    ##解析后输出
#include("header.vm")  ##原样输出，没有经过velocity
```