package mkii.designPattern.strategy;

public abstract class Character {
    // 角色
    String roll;
    // 组合使用武器行为
    WeaponBehavior weapon;

    public void setWeapon(WeaponBehavior weapon){
        this.weapon = weapon;
    }
    public abstract void fight();
}
