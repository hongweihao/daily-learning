package mkii.finger1;

import java.util.ArrayList;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 *
 */
public class FindSequence {
    /**
     * 等差数列 (a1+an)*n/2
     * n^2 + 17n -2S = 0
     *
     * n^2 + (2a1 - 1)n - 2S = 0
     *
     * -B/2 +/- pow(B^2/4 - C)
     *
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (sum <= 0){
            return arrayLists;
        }
        int sum2 = sum * 2;
        // 表示项数
        double n;
        // 等式结果
        double res;
        // 这个for是枚举等差的首项a1
        for (double i = 1; i <= sum >> 1; i ++){
            n = 1;
            res = 0;
            // 循环找到最接近的项
            while (res < sum2){
                n++;
                res = (2 * i + n - 1) * n;
            }
            // 找到符合条件的a1和n
            if (res == (double) sum2){
                ArrayList<Integer> list = new ArrayList<>();
                for (int a = (int) i; a < i + n; a++){
                    list.add(a);
                }
                arrayLists.add(list);
            }
            // 下一个a1
        }
        return arrayLists;
    }

    public static void main(String[] args) {
        FindSequence findSequence = new FindSequence();
        ArrayList<ArrayList<Integer>> lists = findSequence.FindContinuousSequence(3);
        System.out.println(lists.size());
    }
}
