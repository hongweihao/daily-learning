package mkii.designPattern.factory.factorymethod;

import mkii.designPattern.factory.simplefactory.Pizza;

public class ChicagoStyleCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing Chicago Style Cheese Pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking Chicago Style Cheese Pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting Chicago Style Cheese Pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing Chicago Style Cheese Pizza...");
    }
}
