package mkii.designPattern.factory.simplefactory;

public class SimpleFactory {

    public static Pizza createPizza(String type){
        Pizza pizza;
        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("veggie".equals(type)) {
            pizza = new VeggiePizza();
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new PepperoniPizza();
        } else {
            pizza = null;
        }
        return pizza;
    }

}
