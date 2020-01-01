package mkii.designPattern.strategy;

public class King extends Character {

    // 关键在这里，可以动态选择武器
    public King(WeaponBehavior weapon){
        this.roll = "King";
        this.weapon = weapon;
    }

    @Override
    public void fight() {
        System.out.print(roll);
        weapon.useWeapon();
    }
}
