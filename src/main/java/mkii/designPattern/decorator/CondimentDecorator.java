package mkii.designPattern.decorator;

public abstract class CondimentDecorator extends mkii.designPattern.decorator.Beverage {
    Beverage beverage;
    // 让子类必须实现这个方法
    public abstract String getDescription();
}
