package mkii.proxy;

/**
 * 代理类，静态代理
 *
 */
public class StaticAgent implements Sell {

    private Principal principal = new Principal();

    @Override
    public void sell() {
        System.out.println("static");
        principal.sell();
    }
}
