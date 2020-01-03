package mkii.thread.lock;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class RunnableImpl implements Runnable {
    private Lock lock;
    private List<String> list;

    public RunnableImpl(Lock lock, List<String> list) {
        this.lock = lock;
        this.list = list;
    }
    @Override
    public void run() {
        lock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "获得锁..");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(200);
                list.add(name + i);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("释放锁..");
        }
        // 遍历list
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
