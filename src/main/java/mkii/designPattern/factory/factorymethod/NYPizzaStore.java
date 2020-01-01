package mkii.designPattern.factory.factorymethod;

import mkii.designPattern.factory.simplefactory.*;

public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if ("cheese".equals(type)) {
            pizza = new NYStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            pizza = new NYStyleVeggiePizza();
        } else {
            pizza = null;
        }
        return pizza;
    }
}
