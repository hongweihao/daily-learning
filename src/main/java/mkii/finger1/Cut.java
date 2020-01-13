package mkii.finger1;

/**
 * 动态规划
 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
 *
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 *  输入描述:  8
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 * 输出描述:  18
 * 输出答案。
 *
 */
public class Cut {
    // f(n) = max(f(i) * f(n - i)), 1<i<n
    public int cutRope(int target) {
        if (target <= 1){
            return 0;
        }
        if (target == 2){
            return 1;
        }
        if (target == 3){
            return 2;
        }

        // 存储最优解，要注意长度为3的绳子可以剪成1x2，长度为5的绳子可以剪成2x3=6,而不是2x2x1=4，
        // 所以长度超过3的绳子被剪开，且有一段为3的绳子，这段绳子的最优解应该是3，而不是2.
        int[] products = new int[target + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        // 遍历找出乘积最大的方案4~target，从长度为4开始直到找到target就能得到结果
        for (int i = 4; i <= target; i++) {
            int max = 0;
            // 剪成2段，第一段有可能是1~1/2
            for (int j = 1; j <= i / 2; j ++){
                // 不管是多大的，已经是最优的了，就算长度比较大，也都是分割过的
                int product = products[j] * products[i - j];
                if (product > max){
                    max = product;
                }
            }
            products[i] = max;
        }
        return products[target];
    }

    // 贪婪解法：当绳子长度>=5时，尽可能多剪3.当绳子长度为4时，剪成2x2
    public int cutRope2(int target){
        if (target <= 1){
            return 0;
        }
        if (target == 2){
            return 1;
        }
        if (target == 3){
            return 2;
        }
        return recur(target);
    }
    private int recur(int length){
        if (length >= 5){
            return 3 * recur(length - 3);
        }else{
            return length;
        }
    }

    public static void main(String[] args) {
        int l = 6;
        Cut cut = new Cut();
        int max = cut.cutRope2(l);
        System.out.println(max);
    }
}
