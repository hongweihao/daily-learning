package mkii.finger1;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 测试：
 * 1.
 */
public class JumpPlus {

    /**
     * 数列：1,2,4,8,16,32...
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {

        if (target <= 0){
            return 0;
        }

        return 1 << (target - 1);
    }

    public static void main(String[] args) {

        JumpPlus jumpPlus = new JumpPlus();
        System.out.println(jumpPlus.JumpFloorII(6));
    }
}
