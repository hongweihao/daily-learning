package mkii.thread;

import java.util.concurrent.*;

public class Ca implements Callable<String> {

    private String flag;

    Ca(String flag) {
        this.flag = flag;
    }

    @Override
    public String call() throws Exception {

        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(flag);
        }

        return "thread" + flag;
    }

    public static void main(String[] argv) throws Exception {

        Callable a = new Ca("A");
        Callable b = new Ca("B");
        Callable c = new Ca("C");
        Callable d = new Ca("D");
        Callable e = new Ca("E");
        Callable f = new Ca("F");

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
