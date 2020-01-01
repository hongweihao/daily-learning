package mkii.designPattern.strategy;

public class BowAndArrowBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println(" use bow and arrow to fight!");
    }
}