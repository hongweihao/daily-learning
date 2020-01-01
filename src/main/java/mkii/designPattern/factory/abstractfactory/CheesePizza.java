package mkii.designPattern.factory.abstractfactory;

public class CheesePizza extends Pizza {
    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        super(pizzaIngredientFactory);
    }

    @Override
    public void prepare() {
        System.out.println("preparing cheese pizza");

    }
}
