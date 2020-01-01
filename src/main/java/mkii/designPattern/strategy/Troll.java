package mkii.designPattern.strategy;

public class Troll extends Character {

    // 关键在这里
    public Troll(WeaponBehavior weapon){
        this.roll = "Troll";
        this.weapon = weapon;
    }
    @Override
    public void fight() {
        System.out.print(roll);
        weapon.useWeapon();
    }
}