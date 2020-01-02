package mkii.designPattern.singleton;

/**
 * 双重检查锁
 *
 */
public class Singleton4 {
    // 表示instance的update对线程可见，实际上是告诉线程去主存取最新的值
    private static volatile Singleton4 instance;
    private Singleton4(){

    }
    public static Singleton4 getInstance(){
        if (instance == null) {
            // 只有第一次创建实例，多线程争夺资源时才会进入
            synchronized(Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
