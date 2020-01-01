package mkii.finger1;

import com.sun.org.apache.xerces.internal.impl.XMLEntityHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 *
 */
public class Duplication {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        // 检查合法性
        if (numbers == null || length <= 0){
            return false;
        }
        for (int number : numbers) {
            if (number >= length) {
                return false;
            }
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++){
                if (numbers[j] == numbers[i]){
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,0,2,5,3};
        int length = 7;
        int[] duplicate = new int[1];
        Duplication duplication = new Duplication();
        duplication.duplicate(a, length, duplicate);
        System.out.println(duplicate[0]);


        Map<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("1,", 1);
        map.put("2,", 1);
        map.put("3,", 1);
        map.put("4,", 1);

        map.get("2,");

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }

    }

}
