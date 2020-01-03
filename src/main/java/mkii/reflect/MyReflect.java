package mkii.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflect {

    // 根据类名，获取一个实例
    public Object getInstance(String className) throws Exception {

        // 获取类，并不是"对象"
        Class objectClass = Class.forName(className);

        // 获取类的属性
        /*Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }*/

        // 获取类的方法
        /*Method[] methods = objectClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }*/


        // 获取类的构造方法
       /*Constructor constructor = userClass.getConstructor();
        Object object = constructor.newInstance();*/

        Object object = objectClass.newInstance();
        return object;
    }

    public void setValue(Object object, String propertyName, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取类
        Class objectClass = object.getClass();
        // 获取方法
        String name = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Method method = objectClass.getMethod(name, String.class);
        // 执行方法
        method.invoke(object, value);
    }

    public static void main(String[] args) throws Exception {
        String className = "mkii.reflect.User";
        String propertyName = "userName";
        String value = "mkii";

        MyReflect myReflect = new MyReflect();
        Object object = myReflect.getInstance(className);
        myReflect.setValue(object, propertyName, value);

        User user = (User) object;
        System.out.println(user.getUserName());
    }

}
