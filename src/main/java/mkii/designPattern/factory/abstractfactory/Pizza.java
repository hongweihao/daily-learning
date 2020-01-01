package mkii.designPattern.factory.abstractfactory;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Clam clam;
    Pepperoni pepperoni;
    PizzaIngredientFactory pizzaIngredientFactory;

    public Pizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
        this.dough = pizzaIngredientFactory.createDough();
        this.sauce = pizzaIngredientFactory.createSauce();
        this.clam = pizzaIngredientFactory.createClam();
        this.pepperoni = pizzaIngredientFactory.createPepperoni();
    }

    // 准备工作，和面，切菜等
    public abstract void prepare();

    // 烘烤
    public void bake(){
        System.out.println("baking pizza");
    }
    // 切片
    public void cut(){
        System.out.println("cutting pizza");
    }
    // 包装
    public void box(){
        System.out.println("boxing pizza");
    }
}