package mkii.designPattern.factory.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        // 去Chicago的pizza店
        PizzaStore pizzaStore = new ChicagoPizzaStore();
        // 点一个cheese口味的pizza
        pizzaStore.orderPizza("cheese");

        // 去New York的pizza店
        PizzaStore pizzaStore1 = new NYPizzaStore();
        pizzaStore1.orderPizza("cheese");
    }
}
