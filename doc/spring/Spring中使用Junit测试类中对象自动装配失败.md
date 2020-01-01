## 1.实例
`测试类`代码：
```
public class NoticeTest {
    // 这里自动装配失败
    @Autowired
    NoticeService noticeService;

    @Test
    public void getListTest(){
        // 这里可以正常实例化
        // NoticeService noticeService = new NoticeServiceImpl();

        // 这里出现noticeService空指针
        List<PrimaryJob> primaryJobList = noticeService.jobNoticeList();

        System.out.println(primaryJobList.size());
    }
}
```
`NoticeService`实现类代码：
```
@Service
public class NoticeServiceImpl implements NoticeService {

    private final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    NoticeDao noticeDao;

    @Override
    public List<PrimaryJob> jobNoticeList() {

        List<PrimaryJob> primaryJobList = null;
        try {
            primaryJobList = noticeDao.jobNoticeList();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询重点工单失败，发送短信异常！");
            return null;
        }
        return primaryJobList;
    }
}

```



## 2.异常

```
java.lang.NullPointerException
	at com.dq.dao.NoticeTest.getListTest(NoticeTest.java:28)  // noticeService调用方法那一行
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
```
## 3.解决方法
 给`测试类`加上注解
```
// @RunWith注解表示运行在Spring容器中，包括controller，service，dao等
@RunWith(SpringJUnit4ClassRunner.class)
// 这里表示加载配置文件
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
```

> 如果没有加上这两个注解说明，测试类没有运行在Spring容器中，自动装配`noticeservice`对象就会失败了。