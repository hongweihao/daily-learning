package mkii.proxy;

import java.lang.reflect.Proxy;

/**
 * 测试类
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception{

        // 静态代理
        Sell sell1 = new StaticAgent();
        sell1.sell();

        // 动态代理
        DynamicAgent dynamicAgent = new DynamicAgent();
        Sell sell2 = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicAgent);
        sell2.sell();
    }
}
