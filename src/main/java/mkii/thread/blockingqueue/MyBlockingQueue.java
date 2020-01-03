package mkii.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBlockingQueue {
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);
    //private BlockingQueue<String> queue = new ArrayBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(new Provider("provider1", queue)).start();
        new Thread(new Provider("provider2", queue)).start();
        new Thread(new Provider("provider3", queue)).start();
        new Thread(new Provider("provider4", queue)).start();
        new Thread(new Provider("provider5", queue)).start();
        new Thread(new Consumer("consumer1", queue)).start();
        new Thread(new Consumer("consumer2", queue)).start();
        //new Thread(new Consumer("consumer3", queue)).start();
    }
}
