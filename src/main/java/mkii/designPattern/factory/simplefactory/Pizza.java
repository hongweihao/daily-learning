package mkii.designPattern.factory.simplefactory;

public abstract class Pizza {
    // 准备工作，和面，切菜等
    public abstract void prepare();
    // 烘烤
    public abstract void bake();
    // 切片
    public abstract void cut();
    // 包装
    public abstract void box();
}
