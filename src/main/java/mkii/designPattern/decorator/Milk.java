package mkii.designPattern.decorator;

public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription() + "milk, ";
    }
    @Override
    public double cost() {
        return beverage.cost() + 2.5;
    }
}
