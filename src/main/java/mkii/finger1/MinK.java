package mkii.finger1;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * 测试：
 * 1. null | []
 * 2. k <= 0
 * 3. len < k
 * [4,5,1,6,2,7,3,8] 4
 */
public class MinK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> mins = new ArrayList<>(k);
        if (input == null || k <= 0 || input.length < k){
            return mins;
        }
        int temp;
        // 这里使用选择排序，每一轮得到一个最小值，时间复杂度度是n*k。不能使用冒泡，因为冒泡只是两个值之间的交换，需要n*n得出n个值排好序
        for (int i = 0; i < k; i++){
            for (int j = i + 1; j < input.length; j++){
                if (input[i] > input[j]){
                    temp = input[j];
                    input[j] = input[i];
                    input[i] = temp;
                }
            }
            mins.add(input[i]);
        }
        return mins;
    }

    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8};
        int k = 1;
        MinK minK = new MinK();
        minK.GetLeastNumbers_Solution(input, k);
    }
}
