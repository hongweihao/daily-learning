package mkii.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock {
    private static Lock lock = new ReentrantLock();
    private static List<String> list = new ArrayList<>();

    public MyReentrantLock(){
    }

    public static void main(String[] args) {
        MyReentrantLock myReentrantLock = new MyReentrantLock();

        // 启动三个线程往list中添加字符串
        new Thread(new RunnableImpl(lock, list)).start();
        new Thread(new RunnableImpl(lock, list)).start();
        new Thread(new RunnableImpl(lock, list)).start();
    }
}