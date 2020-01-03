package mkii.thread.create;

import java.util.concurrent.*;

public class MyCallable implements Callable<String> {

    private String threadName;

    public MyCallable(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String call() throws Exception {

        for (int i = 0; i < 5; i++){
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(threadName + " printed: " + i);
        }

        return threadName + " - completed";
    }

    public static void main(String[] argv) throws Exception {

        Callable a = new MyCallable("A");
        Callable b = new MyCallable("B");
        Callable c = new MyCallable("C");
        Callable d = new MyCallable("D");
        Callable e = new MyCallable("E");
        Callable f = new MyCallable("F");

        /*FutureTask<String> task1 = new FutureTask<>(a);
        FutureTask<String> task2 = new FutureTask<>(b);*/

        /*new Thread(task1).start();
        new Thread(task2).start();*/

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<String> future1 = threadPool.submit(a);
        Future<String> future2 = threadPool.submit(b);
        Future<String> future3 = threadPool.submit(c);
        Future<String> future4 = threadPool.submit(d);
        Future<String> future5 = threadPool.submit(e);

        // 超过线程池容量，等线程池中线程全部执行完才执行此线程
        Future<String> future6 = threadPool.submit(f);

        /*Thread.sleep(1000);
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());*/

        threadPool.shutdown();



    }
}
