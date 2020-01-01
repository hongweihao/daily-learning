package mkii.proxy;

/**
 * 委托类，被代理类
 *
 */
public class Principal implements Sell {
    @Override
    public void sell() {
        System.out.println("Principal sales");
    }
}
