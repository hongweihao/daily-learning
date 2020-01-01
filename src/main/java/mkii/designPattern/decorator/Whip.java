package mkii.designPattern.decorator;

public class Whip extends CondimentDecorator {
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + "whip, ";
    }
    @Override
    public double cost() {
        return beverage.cost() + 2;
    }
}
