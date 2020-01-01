package mkii.designPattern.decorator;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription() + "mocha, ";
    }
    @Override
    public double cost() {
        return beverage.cost() + 1.5;
    }
}
