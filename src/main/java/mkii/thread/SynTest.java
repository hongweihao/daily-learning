package mkii.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynTest {

    public static void main(String[] args) {

        new Consumer("小明 ").start();
        new Consumer("黄牛 ").start();
        new Consumer("别人 ").start();
    }
}

class BusTicket {

    private int ticketNum = 10;
    private Lock lock = new ReentrantLock();

    /**
     * 如果没加synchronized会出现线程不安全的情况（出现-1张票）
     * <p>
     * synchronized不指定对象，锁的就是this对象
     *
     * @param name
     */
    public void buy(String name) {

        while (true) {
            //lock.lock();
            synchronized(this) {
                if (this.ticketNum > 0) {
                    System.out.println(name + "buy " + ticketNum-- + " ticket");
                } else {
                    System.out.println(name + " unlock!");
                    break;
                }
            }
        }
        //lock.unlock();
    }
}

class Consumer extends Thread {

    private String name;
    static BusTicket busTicket = new BusTicket();

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        busTicket.buy(name);
    }
}
