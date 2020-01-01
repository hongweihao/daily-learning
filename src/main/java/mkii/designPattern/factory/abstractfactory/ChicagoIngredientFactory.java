package mkii.designPattern.factory.abstractfactory;

public class ChicagoIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ChicagoDough();
    }

    @Override
    public Sauce createSauce() {
        return new ChicagoSauce();
    }

    @Override
    public Clam createClam() {
        return new ChicagoClam();
    }

    @Override
    public Cheese createCheese() {
        return new ChicagoCheese();
    }

    @Override
    public Pepperoni createPepperoni() {
        return new ChicagoPepperoni();
    }
}
