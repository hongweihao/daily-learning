package mkii.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Provider implements Runnable{
    private String providerName;
    private BlockingQueue<String> queue;

    public Provider(String providerName, BlockingQueue<String> queue) {
        this.providerName = providerName;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                queue.put(providerName + i);
                System.out.println(providerName + " put the message: " + providerName + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
