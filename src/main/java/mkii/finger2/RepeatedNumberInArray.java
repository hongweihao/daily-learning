package mkii.finger2;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中重复的数字
 * 题目一：找出数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 * 题目二： 不修改数组找出重复的数字
 *
 *
 * 思路：
 * 长度为n，且数字都在0~n-1范围内，如果不存在重复那排序之后就是顺序排列0，1，2，3.。n。
 * 正常来说a[n]==n。如果出现a[n]==x, 则swap(a[n], a[x])，重复直到a[n]==n
 *
 *
 * 测试：
 * 1. 空数组
 * 2. {4, 2, 1, 3, 5}
 * 3. {4, 2, 1, 2, 5}
 * 4. {4, 2, 2, 4, 5}
 * 5. {1, 1, 1, 1, 1}
 * 6. {1}
 * 7. {1, 3}
 *
 */
public class RepeatedNumberInArray {
    // 下标法
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 1){
            return false;
        }
        for (int number : numbers) {
            if (number < 0 || number >= numbers.length){
                return false;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] != i){
                if (numbers[numbers[i]] == numbers[i]){
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, numbers[i], i);
            }
        }
        return false;
    }
    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // hash表法
    public boolean duplicate2(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 1){
            return false;
        }
        for (int number : numbers) {
            if (number < 0 || number >= numbers.length){
                return false;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : numbers) {
            if (map.containsKey(number)){
                duplication[0] = number;
                return true;
            }else {
                map.put(number, 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] t1 = {};
        int[] t2 = {1};
        int[] t3 = {4, 2, 1, 3, 0};
        int[] t4 = {4, 2, 1, 2, 0};
        int[] t5 = {4, 2, 2, 4, 0};
        int[] t6 = {1, 1, 1, 1, 1};
        int[] t7 = {1, 3};

        int[] result = new int[1];

        RepeatedNumberInArray repeatedNumberInArray = new RepeatedNumberInArray();
//        repeatedNumberInArray.duplicate(t1, 0, result);
//        repeatedNumberInArray.duplicate(t2, 1, result);
//        repeatedNumberInArray.duplicate(t3, 5, result);
//        repeatedNumberInArray.duplicate(t4, 5, result);
        repeatedNumberInArray.duplicate2(t5, 5, result);
//        repeatedNumberInArray.duplicate(t6, 5, result);
//        repeatedNumberInArray.duplicate(t7, 2, result);
        System.out.println(result[0]);

    }
}
