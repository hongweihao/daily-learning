## 引入依赖
```
	<!-- 使用rabbitMQ -->
    <dependency>
      <groupId>org.springframework.amqp</groupId>
      <artifactId>spring-rabbit</artifactId>
      <version>2.1.4.RELEASE</version>
    </dependency>
```

## MQ配置文件rabbitMQ.xml
> 在`web.xml`中引用

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/rabbit
   http://www.springframework.org/schema/rabbit/spring-rabbit.xsd
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 定义RabbitMQ的连接工厂 -->
    <rabbit:connection-factory id="connectionFactory" host="127.0.0.1" port="5672" username="mkii" password="mkii1234"/>

    <!-- MQ的管理，包括队列、交换器等 -->
    <rabbit:admin connection-factory="connectionFactory" />

    <!-- 定义队列，自动声明 -->
    <rabbit:queue name="zpcQueue" auto-declare="true"/>

    <!-- 定义交换器，把Q绑定到交换机，自动声明 -->
    <rabbit:fanout-exchange name="fanoutExchange" auto-declare="true">
        <rabbit:bindings>
            <rabbit:binding queue="zpcQueue"/>
        </rabbit:bindings>
    </rabbit:fanout-exchange>

    <!-- 定义Rabbit模板，指定连接工厂以及定义exchange -->
    <rabbit:template id="amqpTemplate" connection-factory="connectionFactory" exchange="fanoutExchange" />

    <!-- 队列监听 -->
    <rabbit:listener-container connection-factory="connectionFactory">
        <rabbit:listener ref="foo" method="listen" queue-names="zpcQueue" />
    </rabbit:listener-container>

    <bean id="foo" class="com.dq.util.Foo" />

</beans>

```
## 消费者类
```
public class Foo {
    /**
     * 具体执行的方法
     *
     * @param foo
     */
    public void listen(String foo) {
        System.out.println("\n消费者： " + foo + "\n");
    }
}
```
## 生产者测试类
```
public class RecvTest {

    @Test
    public void test() throws Exception{

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-rabbitmq.xml");

        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);

        //发送消息
        template.convertAndSend("Spring整合rabbitMQ!");

        Thread.sleep(1000);// 休眠1秒
        ctx.destroy(); //容器销毁
    }
}
```
> 运行测试类，成功

![](http://ww1.sinaimg.cn/large/006fJlVugy1g31zkdnmmwj310v0ahq4n.jpg)
## 参考
教程: [https://blog.csdn.net/hellozpc/article/details/81436980](https://blog.csdn.net/hellozpc/article/details/81436980).

解决ListenerContainerFactoryBean初始化失败: [https://ask.csdn.net/questions/710341](https://ask.csdn.net/questions/710341).