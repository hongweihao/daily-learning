package mkii.designPattern.factory.simplefactory;

public class PizzaStore {
    public void orderPizza(String type) {

        // 决定做一个什么类型的pizza
        Pizza pizza = SimpleFactory.createPizza(type);

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
}
