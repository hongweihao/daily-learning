> Java日志两个阵营：slf4j和common-loggin
>
> slf4j的实现有logback（其他的日志框架需要适配器才能与slf4j结合使用）
>
> common-loggin的实现有log4j/JDK Logging/log4j 2等

> slf4j：
> 只提供一个核心slf4j api(就是slf4j-api.jar包)，这个包只有日志的接口，并没有实现

> common-loggin:
>
> Apache基金会所属的项目，是一套Java日志接口。为了整合log4j和JDK Loggin
>
> slf4j+log4j组合使用模式：
> slf4j-api-1.5.11.jar/log4j-1.2.15.jar/slf4j-log4j12-1.5.11.jar（适配器层jar）
>
> commons-logging+Log4J组合使用模式：
> commons-logging-1.1.jar/log4j-1.2.15.jar

比较常用的组合使用方式是Slf4j与Logback组合使用，Commons Logging与Log4j组合使用。



#### 不同的获取logger的方式
> 面向接口编程
>
> slf4j+log4j：
> ```rg.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(xx.class);```
>
> jcl+log4j:
> ```org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(xx.class);```

[Java常用日志框架介绍](https://mp.weixin.qq.com/s/FWnh71eN5jxiu7aBOiUHaA)

[log4j与commons-logging，slf4j的关系](https://www.cnblogs.com/zhuawang/p/3999132.html)

[Java日志框架：slf4j作用及其实现原理](https://www.cnblogs.com/xrq730/p/8619156.html)