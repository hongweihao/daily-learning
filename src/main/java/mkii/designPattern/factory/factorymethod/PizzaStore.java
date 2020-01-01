package mkii.designPattern.factory.factorymethod;

import mkii.designPattern.factory.simplefactory.Pizza;

public abstract class PizzaStore {
    public void orderPizza(String type) {

        // 决定做一个什么类型的pizza
        Pizza pizza = createPizza(type);

        // 还没有这个类型的pizza
        if (pizza == null) {
            System.out.println("please chose a pizza type from menu!");
            return;
        }

        // 其他工序
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
    // 交由子类觉得实例化的类型
    public abstract Pizza createPizza(String type);
}
