package mkii.designPattern.factory.abstractfactory;

public class NYIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new NYDough();
    }

    @Override
    public Sauce createSauce() {
        return new NYSauce();
    }

    @Override
    public Clam createClam() {
        return new NYClam();
    }

    @Override
    public Cheese createCheese() {
        return new NYCheese();
    }

    @Override
    public Pepperoni createPepperoni() {
        return new NYPepperoni();
    }
}
