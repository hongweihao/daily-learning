package mkii.designPattern.singleton;

/**
 * 懒汉式-延迟实例化
 *
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){

    }
    public static Singleton2 getInstance(){
        if (instance == null) { // 这里会出现多线程问题
            instance = new Singleton2();
        }
        return instance;
    }
}
