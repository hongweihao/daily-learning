package mkii.designPattern.strategy;

public class KnifeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println(" use knife to fight!");
    }
}