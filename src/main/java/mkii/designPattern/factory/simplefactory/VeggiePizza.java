package mkii.designPattern.factory.simplefactory;

public class VeggiePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing veggie pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking veggie pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting veggie pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing veggie pizza...");
    }
}
