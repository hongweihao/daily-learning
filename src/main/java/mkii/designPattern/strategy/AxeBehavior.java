package mkii.designPattern.strategy;

public class AxeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println(" use Axe to fight!");
    }
}
