package mkii.socket;

import lombok.SneakyThrows;
import mkii.rpc.entity.Request;

import java.lang.reflect.Method;

/**
 * 通过request实例，利用反射机制调用对应方法
 *
 */
public class Handler {

    @SneakyThrows
    public Object serverHandler(Request request){
        // 获取需要调用的类和方法
        String className = request.getClassName();
        String methodName = request.getMethodName();
        Class[] parameterTypes = request.getParamTypes();
        Object[] parameters = request.getParameters();

        // 通过反射机制调用
        Class<?> aClass = Class.forName(className);
        Method method = aClass.getMethod(methodName, parameterTypes);
        Object o = aClass.newInstance();
        return method.invoke(o, parameters);
    }
}
