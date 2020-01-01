package mkii.finger1;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 测试:
 * 1. null | []
 * 2. [1]
 * 3. [122222234]
 */
public class HalfNumber {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length <= 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        int len = array.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : array) {
            if (map.containsKey(i)){
                int count = map.get(i) + 1;
                if (count<<1 > len){
                    return i;
                }
                map.put(i, count);
            }else{
                map.put(i, 1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {3};
        HalfNumber halfNumber = new HalfNumber();
        System.out.println(halfNumber.MoreThanHalfNum_Solution(array));
    }
}
