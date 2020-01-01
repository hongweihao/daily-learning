package mkii.finger1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 *
 *
 */
public class FirstAppear {
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() <= 0){
            return -1;
        }

        char c;
        int i; // 当前被查找的字符指针
        int j; // 在i之后的位置查看是否有相同的

        // 已经查过的字符就跳过
        Map<Character, Integer> map = new HashMap<>();

        for (i = 0; i < str.length(); i++){
            c = str.charAt(i);
            if (map.containsKey(c)){
                continue;
            }else {
                map.put(c, 1);
            }
            for (j = i + 1; j < str.length(); j++){
               if (str.charAt(j) == c){
                   break;
               }
            }
            if (j == str.length()){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FirstAppear firstAppear = new FirstAppear();
        String str = "ghahgagajg";
        int i = firstAppear.FirstNotRepeatingChar(str);
        System.out.println(str.charAt(i));
    }
}
