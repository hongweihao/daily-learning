package mkii.finger1;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 本质与青蛙跳台阶一样
 *
 * 测试：
 * 1.
 */
public class RectangleCover {

    public int RectCover(int target) {

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

    }
}
