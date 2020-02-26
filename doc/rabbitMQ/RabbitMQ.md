## RabbitMQ面试题

### 1. 什么是RabbitMQ？

> 基于AMQP协议，使用erlang编写的企业级消息队列。



### 2. 使用场景
> 1. 异步通信：短信、Email
> 2. 解耦：发送方和接收方解耦
> 3. 削峰：队列存储消息，慢慢处理



### 3. 角色
> - 生产者：push data and message to queue
>
> - 消费者：deal with data and confirm message
>
> - 代理：RabbitMQ 本身，“快递”角色
>
>   provider/consumer/exchange/queue



### 4. 重要组件

> - `ConnectFactory`
> - `Channel`
> - `Exchange`
> - `Queue`
> - `RoutingKey`
> - `BindingKey`



### 5. vhost 的作用

> 



