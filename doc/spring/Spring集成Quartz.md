#### 1.引入依赖
```
<!-- 定时作业调度quartz -->
<dependency>
  	<groupId>org.quartz-scheduler</groupId>
  	<artifactId>quartz</artifactId>
  	<version>2.2.1</version>
</dependency>
```
```
<!-- 我的spring版本 -->
<spring.version>4.3.5.RELEASE</spring.version>
```

#### 2.Quartz配置文件
> 配置文件`spring-quartz.xml`需要在web.xml中引入
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">

<beans>
    <!-- 使用MethodInvokingJobDetailFactoryBean，任务类可以不实现Job接口，通过targetMethod指定调用方法-->
    <!-- 定义目标bean和bean中的方法 -->
    <bean id="SpringQtzJob" class="com.dq.service.impl.NoticeServiceImpl"/>
    <bean id="SpringQtzJobMethod" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <property name="targetObject">
            <ref bean="SpringQtzJob"/>
        </property>
        <property name="targetMethod">  <!-- 要执行的方法名称 -->
            <value>quartzTestMethod</value>
        </property>
    </bean>

    <!-- ======================== 调度触发器 ======================== -->
    <bean id="CronTriggerBean" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="SpringQtzJobMethod"></property>
        <!-- 每一分钟执行一次 -->
        <property name="cronExpression" value="0 /1 * * * ? *"></property>

    </bean>

    <!-- ======================== 调度工厂 ======================== -->
    <bean id="SpringJobSchedulerFactoryBean" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="CronTriggerBean"/>
            </list>
        </property>
    </bean>
</beans>
```
#### 3.执行类`NoticeServiceImpl`
```
@Service
public class NoticeServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
    //  执行方法
    public void quartzTestMethod(){
        logger.debug("定时作业调度！quartz  ****");
    }
}
```
#### 4.执行效果
> 每隔一分钟打印一次`定时作业调度！quartz  ****`
```
15:36:00.079 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
15:36:00.079 [SpringJobSchedulerFactoryBean_Worker-1] DEBUG org.quartz.core.JobRunShell - Calling execute on job DEFAULT.SpringQtzJobMethod
15:36:00.110 [SpringJobSchedulerFactoryBean_Worker-1] DEBUG c.dq.service.impl.NoticeServiceImpl - 定时作业调度！quartz  ****
15:36:26.496 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
15:36:50.985 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 1 triggers
15:37:00.005 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
15:37:00.005 [SpringJobSchedulerFactoryBean_Worker-2] DEBUG org.quartz.core.JobRunShell - Calling execute on job DEFAULT.SpringQtzJobMethod
15:37:00.005 [SpringJobSchedulerFactoryBean_Worker-2] DEBUG c.dq.service.impl.NoticeServiceImpl - 定时作业调度！quartz  ****
15:37:27.556 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
15:37:52.208 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 1 triggers
15:38:00.000 [SpringJobSchedulerFactoryBean_Worker-3] DEBUG org.quartz.core.JobRunShell - Calling execute on job DEFAULT.SpringQtzJobMethod
15:38:00.000 [SpringJobSchedulerFactoryBean_Worker-3] DEBUG c.dq.service.impl.NoticeServiceImpl - 定时作业调度！quartz  ****
15:38:00.000 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
15:38:24.841 [SpringJobSchedulerFactoryBean_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
```
#### 5.参考
[https://kevin19900306.iteye.com/blog/1397744](https://kevin19900306.iteye.com/blog/1397744)  

Cron表达式工具：[http://cron.qqe2.com/](http://cron.qqe2.com/)
