package mkii.designPattern.singleton;

/**
 * 同步getInstance()方法
 */
public class Singleton3 {
    private static Singleton3 instance;
    private Singleton3(){

    }
    // 每次访问实例都会经历锁，如果频繁访问性能差
    public synchronized static Singleton3 getInstance(){
        if (instance == null) {
            instance = new Singleton3();
        }
    return instance;
    }
}
