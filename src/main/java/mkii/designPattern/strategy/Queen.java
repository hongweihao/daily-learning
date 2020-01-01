package mkii.designPattern.strategy;

public class Queen extends Character {

    // 关键在这里
    public Queen(WeaponBehavior weapon){
        this.roll = "Queen";
        this.weapon = weapon;
    }
    @Override
    public void fight() {
        System.out.print(roll);
        weapon.useWeapon();
    }
}