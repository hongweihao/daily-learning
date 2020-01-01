package mkii.designPattern.factory.factorymethod;

import mkii.designPattern.factory.simplefactory.Pizza;

public class ChicagoStyleVeggiePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing  Chicago Style veggie pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking  Chicago Style veggie pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting  Chicago Style veggie pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing  Chicago Style veggie pizza...");
    }
}
