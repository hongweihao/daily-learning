package mkii.designPattern.decorator;

public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription() + "soy, ";
    }
    @Override
    public double cost() {
        return beverage.cost() + 1;
    }
}
