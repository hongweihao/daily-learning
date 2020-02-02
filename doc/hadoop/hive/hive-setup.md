### hive的安装

> hive是一个工具，不存在集群的说法，只是把`hiveQL`语句翻译之后交给集群运行
> hive与数据库表面的唯一区别是hive不能`insert`，因为hive的数据源是`hdfs`，不支持插入一行一行的数据



#### 1. 准备hive包

[hive下载地址](http://www.apache.org/dyn/closer.cgi/hive/ )

```shell
# 解压
tar -zxvf hive-0.12.0.tar.gz
```



#### 2. 使用MySQL作为hive的元数据库

> 要先保证安装了MySQL数据库
>
> 准备MySQL驱动包 [下载地址](http://www.mysql.com/downloads/connector/j/ )

```shell
# 解压并复制到hive解压包的lib目录下
cp mysql-connector-java-5.1.28.jar ${HIVE_HOME}/lib
```

> 在`${HIVE_HOME}/conf`目录下新建`hive-site.xml`文件(不需要xml头之类的内容)

```xml
<configuration>
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://node01:3306/hive?createDatabaseIfNotExist=true</value>
    <description>JDBC connect string for a JDBC metastore</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
    <description>Driver class name for a JDBC metastore</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>hiver</value>
    <description>username to use against metastore database</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>hadoop</value>
    <description>password to use against metastore database</description>
  </property>
</configuration>
```



#### 3. 创建数据库用户并分配权限

> 在MySQL终端中操作

```sql
-- 创建用户
create user 'hiver' identified by 'hadoop'

-- 分配权限
grant all privileges on *.* to 'hiver'@''%' identified by 'hadoop'; 

-- 刷新，生效
flush privileges；
```



#### 4. 初始化`Metastore `架构 

```shell
schematool -dbType mysql -initSchema
```



#### 5. [hive 用法](./hive.md)



参考：[Ubuntu安装hive，并配置mysql作为元数据库](http://dblab.xmu.edu.cn/blog/install-hive/ )



