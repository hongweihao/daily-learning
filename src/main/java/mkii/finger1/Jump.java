package mkii.finger1;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 测试：
 * 1.n=0
 * 2.n
 */
public class Jump {

    /**
     * 数列：1,2,3,5,8,13
     * @param target
     * @return
     */
    public int JumpFloor(int target) {

        if (target <= 0)
            return 0;

        if (target == 1)
            return 1;

        if (target == 2)
            return 2;

        int n_1 = 2;
        int n_2 = 1;
        int temp;

        for (int i = 3; i <= target; i++){
            temp = n_1;
            n_1 = n_1 + n_2;
            n_2 = temp;
        }
        return n_1;
    }

    public static void main(String[] args) {

        Jump jump = new Jump();
        System.out.println(jump.JumpFloor(6));

    }
}
