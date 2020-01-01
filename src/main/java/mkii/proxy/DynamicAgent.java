package mkii.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理的代理类，不再与委托类实现同一个接口
 *
 */
public class DynamicAgent implements InvocationHandler {

    private Object factory = new Principal();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 代理的作用是运行这一行
        System.out.println("dynamic");

        return method.invoke(factory, args);
    }
}
