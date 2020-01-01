package mkii.designPattern.factory.simplefactory;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing cheese pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking cheese pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting cheese pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing cheese pizza...");
    }
}
