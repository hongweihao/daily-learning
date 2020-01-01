package mkii.designPattern.strategy;

public class Knight extends Character {

    // 关键在这里
    public Knight(WeaponBehavior weapon){
        this.roll = "Knight";
        this.weapon = weapon;
    }
    @Override
    public void fight() {
        System.out.print(roll);
        weapon.useWeapon();
    }
}