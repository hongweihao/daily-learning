package mkii.designPattern.decorator;


public class DarkRoast extends Beverage {
    public String getDescription(){
       return "dark roast, ";
    }
    @Override
    public double cost() {
        return 10;
    }
}
