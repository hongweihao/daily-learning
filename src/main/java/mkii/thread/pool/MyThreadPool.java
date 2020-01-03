package mkii.thread.pool;

import mkii.thread.create.MyCallable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建线程池的5中方式
 *
 */
public class MyThreadPool {

    // 5种创建线程池的方式
    public ExecutorService createThreadPool(){
        // 获取JVM可以使用的cpu数量
        int cpuNums = Runtime.getRuntime().availableProcessors();

        // 线程池中只有一个线程，线程顺序执行
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 缓存线程池，线程并行运行。线程超过60s未使用会被回收
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 固定线程数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(cpuNums);

        // 用来调度即将执行的任务的线程池
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(cpuNums);

        // 只有一个线程，用来调度执行将来的任务
        ExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();

        return fixedThreadPool;
    }

    // 用runnable/callable创建的线程放入线程池
    public void putOnPool() throws ExecutionException, InterruptedException {
        ExecutorService executor = createThreadPool();

        ArrayList<Future> arrayList = new ArrayList<>();
        Future<?> future = null;

        for (int i = 0; i < 6; i++){
            // 提交runnable类型的线程到线程池，返回值是void。不需要等待返回值
            //future = executor.submit(new MyRunnable("MyRunnable" + i));

            // 提交callable类型的线程到线程池，需要等待返回值，所以是阻塞的？？？
            future = executor.submit(new MyCallable("MyCallable" + i));

            // 结果放入arrayList中
            arrayList.add(future);
        }

        for (Future f : arrayList) {
            System.out.println(f.get());
        }

        executor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.putOnPool();
    }
}
