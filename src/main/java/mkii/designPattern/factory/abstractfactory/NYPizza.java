package mkii.designPattern.factory.abstractfactory;

public class NYPizza extends Pizza {
    public NYPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        super(pizzaIngredientFactory);
    }

    @Override
    public void prepare() {
        System.out.println("preparing NY pizza");
    }
}
