package mkii.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * queue的消费者
 *
 */
public class Consumer implements Runnable {
    private String consumerName;
    private BlockingQueue<String> queue;

    public Consumer(String consumerName, BlockingQueue<String> queue) {
        this.consumerName = consumerName;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!queue.isEmpty()) {
                String message = queue.take();
                System.out.println(consumerName + " got the message: " + message);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
