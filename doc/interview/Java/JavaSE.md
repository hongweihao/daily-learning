## `JavaSE` 面试题整理

### 1. 接口和抽象类的区别？

> 接口：可以有变量和方法。变量会被隐式设置为public static final，
>
> 方法会被隐式设置为public abstract
>
> 抽象类：抽象方法必须是public/protect，如果是private，则不能被子类继承，无法实现方法
>
> 抽象类不能创建对象
>
> 子类实现抽象类，且不实现抽象方法，则子类也为抽象类



### 2. 重载与重写的区别？

> 重载：方法的名称相同，存在参数列表不同的情况，无法以返回值不同作为区分条件。
>
> 一个类中多态性的一种表现
>
> 静态
>
> 重写：子类继承父类的方法，并重写方法体，覆盖继承而来的方法。



### 3. 两个对象的 `hashcode()` 相同，则 `equals()` 结果一定为true吗？

> 是
>
> `hashcode()` ：通过将该对象的内部地址转换成一个整数
>
> 为什么会出现不同对象的 `hashcode()` 返回相同？
>
> `hashcode()` 方法返回的是 `int` 类型， Java中 `int` 的范围(2^-31 ~ 2 ^31 -1)，完全有可能重复



### 4. Object类中的方法

> `wait()`  释放锁，进入的等待池
>
> `notify()`  随机通知一个线程进入锁池
>
> `notifyAll()`   通知所有线性进入锁池
>
> `clone()`   创建并返回对象的一个副本
>
> `hashcode()`  返回对象的hash码
>
> `equals()`  比较对象是否’相等‘
>
> `toString()` 对象的字符串表示
>
> `finalize()`   gc如果发现对象没有引用，那么gc就会调用这个方法回收对象



### 5. Java中的异常结构

原文：[Java中的异常结构](https://juejin.im/post/5cf2300e5188254aeb17ea26)![Throwable.png](http://ww1.sinaimg.cn/large/006fJlVugy1gcp7r0ltzbj30ks0dmdgq.jpg)



### 6. try、catch、finally、return、throw的执行顺序

> 除非在 `try/catch` 块中使用 `System.exit(1)` (退出JVM)，否则 `finally` 块总会被执行
>
> 如果 `finally` 块中含有 `return/throw` 语句，则 `try/catch` 中的 `return/throw` 失效
>
> `finally` 块在 `try` 的 `return/throw` 之前执行



### 7. char 类型可以存储汉字吗？

> char中存储的是Unicode编码的字符，Unicode中包含汉字，是可以存的。如果某个特殊的汉字不被Unicode所包含，那么存储不了。
>
> Unicode编码占用两个字节，所以char变量占用两个字节



### 8. 什么是序列化？什么情况下需要序列化？

> 序列化：将Java对象转换成字节流的过程
>
> 当java对象需要在网络上传输或持久化存储到文件中时，就需要序列化（远程调用，不同JVM）
>
> 标记接口 Serializable： 告诉JVM这个类对象可以被序列化



### 9. Java中的泛型擦除

> 编译后的字节码文件(.class)中已经被替换为原生类型。
>
> ```java
> List<String> l1 = new ArrayList<String>();
> 
> List<Integer> l2 = new ArrayList<Integer>();
> 
> System.out.println(l1.getClass() == l2.getClass()); // true
> ```



### 10. Java中创建子类的实例时会创建父类实例吗？

> 创建子类对象时，会把父类中的属性和方法加载进内存（这里仅仅调用了父类的构造方法而没有创建父类的对象）
>
> 总之，会为父类分配堆内存，但这块内存属于子类的堆内存。



### 11. Java中的引用

> 强引用：`Object o = new Object();` 只要强引用还存在，JVM就永远不会回收
>
> 弱引用：用来描述一些还有用但非必要的对象。在内存即将发生内存溢出之前，会把这些对象列进回收范围之中进行二次垃圾回收。
>
> 软引用：描述非必需对象。被弱引用关联的对象只能存活到下一次垃圾收集发生之前。当进行垃圾回收时，无论当前内存是否足够，都会回收。
>
> 虚引用：虚引用必须和引用队列 `ReferenceQueue` 联合使用。



### 12. Java对象创建过程

> 1. 检查类是否已经被加载
>
> 2. 给对象分配内存空间(指针碰撞-CAS/空闲链表)
>
> 3. 零值初始化（0/null）
>
> 4. 设置对象头（JVM进行信息标记：分代年龄）
>
> 5. <init>方法（执行构造方法）

