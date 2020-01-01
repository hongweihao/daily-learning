package mkii.designPattern.factory.simplefactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 到pizza店
        PizzaStore pizzaStore = new PizzaStore();

        // 点一个cheese pizza
        pizzaStore.orderPizza("cheese");

        // 点一个veggie pizza
        pizzaStore.orderPizza("veggie");
    }
}
