package mkii.designPattern.factory.abstractfactory;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Clam createClam();
    Cheese createCheese();
    Pepperoni createPepperoni();
    // ...
}
