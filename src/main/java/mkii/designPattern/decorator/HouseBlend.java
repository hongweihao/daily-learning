package mkii.designPattern.decorator;

public class HouseBlend extends Beverage {
    public String getDescription(){
        return "house blend, ";
    }
    @Override
    public double cost() {
        return 12;
    }
}
