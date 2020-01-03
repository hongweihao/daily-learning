package mkii.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReentrantReadWriteLock {
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        /*readWriteLock.readLock().lock();
        readWriteLock.readLock().unlock();

        readWriteLock.writeLock().lock();
        readWriteLock.writeLock().unlock();*/
    }
}