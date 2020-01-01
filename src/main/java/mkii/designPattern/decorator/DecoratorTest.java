package mkii.designPattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        // 点2杯咖啡
        Beverage darkRoast = new DarkRoast();
        Beverage houseBlend = new HouseBlend();

        // 加奶泡、加摩卡
        darkRoast = new Whip(darkRoast);
        darkRoast = new Whip(darkRoast);
        darkRoast = new Mocha(darkRoast);

        // 加豆浆、牛奶
        houseBlend = new Soy(houseBlend);
        houseBlend = new Milk(houseBlend);

        // 计费
        System.out.print(darkRoast.getDescription());
        System.out.println(darkRoast.cost());

        System.out.print(houseBlend.getDescription());
        System.out.println(houseBlend.cost());
    }
}
