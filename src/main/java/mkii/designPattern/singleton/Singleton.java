package mkii.designPattern.singleton;

/**
 * 饿汉式-急切实例化
 * 饿汉式的缺点就是，可能在还不需要此实例的时候就已经把实例创建出来了，没起到lazy loading的效果。
 * 优点就是实现简单，而且安全可靠。
 */
public class Singleton {
    static Singleton instance = new Singleton();
    private Singleton(){
    }
    public static Singleton getInstance(){
        return instance;
    }
}
