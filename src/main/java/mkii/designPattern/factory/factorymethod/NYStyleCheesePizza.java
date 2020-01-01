package mkii.designPattern.factory.factorymethod;

import mkii.designPattern.factory.simplefactory.Pizza;

public class NYStyleCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing NY Style cheese pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking NY Style cheese pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting NY Style cheese pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing NY Style cheese pizza...");
    }
}
