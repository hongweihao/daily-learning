> rabbitMQ的安装可以参考我另一篇博文>>[centos7下通过rpm搭建rabbitMQ环境](https://blog.csdn.net/mkii_hong/article/details/88140369)
## 引入依赖
这里只是展示两者的整合，更多关于rabbitMQ的使用请访问[https://www.rabbitmq.com/](https://www.rabbitmq.com/)

		<!-- 引入rabbitmq jar-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
## 增加rabbitmq配置
## rabbitmq配置
这里的端口是5672，15672是管理端的端口

```
spring.application.name=spring-boot-rabbitmq
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=mkii
spring.rabbitmq.password=mkii1234
```
## 生产
```
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
```

## 消费
```
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
```
## 测试类
```
import com.zhimi.recruit.rabbit.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void hello() throws Exception {

        // 创建队列
        Queue queue = new Queue("hello");

        helloSender.send();
    }
}
```

## 参考
[http://www.cnblogs.com/hlhdidi/p/6535677.html](http://www.cnblogs.com/hlhdidi/p/6535677.html)