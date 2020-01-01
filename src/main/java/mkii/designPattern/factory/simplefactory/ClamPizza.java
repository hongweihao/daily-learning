package mkii.designPattern.factory.simplefactory;

public class ClamPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing clam pizza...");
    }

    @Override
    public void bake() {
        System.out.println("baking clam pizza...");
    }

    @Override
    public void cut() {
        System.out.println("cutting clam pizza...");
    }

    @Override
    public void box() {
        System.out.println("boxing clam pizza...");
    }
}
