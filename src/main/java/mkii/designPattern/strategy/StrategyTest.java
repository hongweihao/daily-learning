package mkii.designPattern.strategy;

/**
 * 场景：一个游戏中有4种角色，国王(King)、皇后(Queen)、骑士(Knight)、巫师(Troll)。
 * 每个角色每次只能选择使用一种武器，但是可以在游戏过程种换武器。
 * 武器4种：匕首(Knife)、弓箭(Bow and arrow)、斧头(Axe)、宝剑(sword)
 *
 */
public class StrategyTest {
    public static void main(String[] args) {

        // 有4种武器可以挑选
        WeaponBehavior sword = new SwordBehavior();
        WeaponBehavior knife = new KnifeBehavior();
        WeaponBehavior axe = new AxeBehavior();
        WeaponBehavior bowAndArrow = new BowAndArrowBehavior();

        // 有4个角色，它们选择各自的武器
        Character king = new King(sword);
        Character queen = new Queen(knife);
        Character knight = new Knight(axe);
        Character troll = new Troll(bowAndArrow);

        // 攻击
        king.fight();
        queen.fight();
        knight.fight();
        troll.fight();

        // 换武器
        troll.setWeapon(knife);
        troll.fight();
    }
}
