## Java 多线程

#### 1. 创建线程的方式

> 1. extents Thread
> 2. implement Runnable
> 3. Callable & Future（线程池）



#### 2. 有几种线程池？

> 



#### 3. Java等待池和锁池

等待池

> 一个线程`A`调用`object`对象的`wait()`方法，`A`就会释放该对象的锁，进入该对象的等待池，等待池中的线程不会去竞争该对象锁。  
>
> 如果另外的一个线程调用了相同对象的`notifyAll()`方法，那么处于该对象的等待池中的线程就会全部进入该对象的锁池中，准备争夺锁的拥有权。  
>
> 如果另外的一个线程调用了相同对象的`notify()`方法，那么仅仅有一个处于该对象的等待池中的线程(随机)会进入该对象的锁池.  

锁池

> 只有获取了对象的锁才能去执行对象的`synchronize`代码，对象的锁每次只有一个线程可以获得，其他线程只能在锁池等待。



#### 4. sleep()和wait()的区别

> sleep()：是Thread类的静态方法，让线程进入睡眠状态，**不释放对象锁 **，让出CPU
>
> wait()：是Object类的方法，让线程**释放对象锁 **并进入等待池



#### 5. 线程同步的方式有哪些？

> `synchronized`：同步方法/同步代码块
>
> `volatile`：可见变量
>
> `ReentrantLock`类
>
> `ThreadLocal`：并不是真正的同步，只是副本



#### 6. volatile和synchronized的区别

> volatile/final/synchronized都可以实现可见性
>
> volatile的本质是告诉JVM，当前的变量值不确定（寄存器），需要从主存读取最新的值
>
> synchronized是锁住当前变量，只有当前线程可以访问，其他线程访问时被阻塞
>
> volatile只能用在变量 | synchronized可以用在变量/代码块/方法
>
> volatile仅能实现变量可见性，不具备原子性 | synchronized保证可见性，原子性
>
> 其他线程访问volatile修饰的变量不会阻塞 | 其他线程访问synchronized的临界区会阻塞



#### 7. Java实现生产者消费者的三种方法

[java实现生产者消费者问题 ](https://www.cnblogs.com/happyPawpaw/archive/2013/01/18/2865957.html)

> 1、wait() 和notify()
>
> :::shareArea/producer(consumer) extends thread
>
> producer:{
>
> while(shareArea.size == MaxSize)： shareArea.wait()
>
> else ：write..  -> shareArea.notify()
>
> }
>
> consumer:{
>
> while(shareArea.size == MinSize)： shareArea.wait()
>
> else： read.. -> shareArea.notify()
>
> }
>
> 2、await() 和 signal()
>
> ::::lock = new ReentrantLock() -->full(empty) = lock.newCondition()
>
> producer:{
>
>  while(true){ 
>
> lock.lock()
>
> while(shareArea.size == MaxSize)： shareArea.wait()
>
> else ：write..  -> shareArea.notify()
>
> lock.unlock()
>
>  }
>
> }
>
> consumer:{
>
>  while(true){ 
>
> lock.lock()
>
> while(shareArea.size == MinSize)： shareArea.wait()
>
> else： read.. -> shareArea.notify()
>
> lock.unlock()
>
>  }
>
> }
>
> 3、使用阻塞队列 LinkedBlockingQueue
>
> queue.put(o)
>
> o = queue.take()



#### 8. ReentrantLock

> java.util.concurrent.locks包中的一个可重入锁类。基于AQS。高竞争条件下有很好的性能，可中断。
>
> 1.选择锁：可以通过构造方法选择是否使用公平锁，默认为非公平锁。
>
> return sync = fair ? new FairSync() : new NoFairSync();
>
> 2.获取锁：
>
> （1）非公平锁
>
> a.查看status，if(status==0)表示无线程占用锁，设置exclusiveOwnerThread为当前线程，return true
>
> b.if(status!=0)表示有线程占用锁，判断是否当前线程，如果不是return false，线程进入自旋。如果是当前线程，那就是线程重入，重入数+1，return true
>
> （2）公平锁
>
> 不会先尝试当前锁是否能够抢占，而是直接call acquire方法。
>
> if(status==0) 先call hasQueuedPredecessors()再call compareAndSetStatus(0, acquire)
>
> \3. 公平锁与非公平锁的区别
>
> 公平锁获取锁的过程是FIFO，先进入队列中的阻塞线程先获得锁。
>
> 公平锁能解决线程饥饿问题，但会导致线程频繁切换，减低多线程的效率。
>
> 非公平锁的吞吐量大于公平锁。



#### 9. AbstractQueuedSynchronized(AQS)

> AQS提供了一个基于FIFO的队列，可以用于构建阻塞锁或同步锁的基础框架。
>
> AQS的子类一般只需要重写tryAcquire(int)和tryRelease(int)两个方法即可(使用模板方法模式)
>
> tryAcquire方法试图在独占模式下获取对象状态。可以用此方法实现lock.tryLock()
>
> Acquire方法以独占的模式获取对象，忽略中断。通过至少调用一次tryAcquire实现，并在成功时返回。否则成功之前一直调用tryAcquire。可以用此方法实现lock.lock()
>
> Release方法以独占模式释放对象。如果tryRealease方法放回true则通过消除一个或多个线程的阻塞实现。可以用此方法实现lock.unlock()



