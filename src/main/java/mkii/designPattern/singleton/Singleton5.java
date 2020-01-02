package mkii.designPattern.singleton;

/**
 * 枚举单例
 * 我们定义的一个枚举，在第一次被真正用到的时候，会被虚拟机加载并初始化，而这个初始化过程是线程安全的。
 */
public enum Singleton5  {
    // 直接使用Singleton5.instance获取实例，不需要getInstance()方法
    instance;
    public void method(){
        System.out.println(instance.getClass().getName());
    }
}
