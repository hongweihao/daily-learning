package mkii.finger1;
import java.util.ArrayList;

/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。  ？？？
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 *
 * 测试：
 * 1. null | ""
 * 2. length = 9
 * 3. 数字+字母
 * 4. 字母
 * 5. 标点符号
 *
 */
public class SortString {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str == null || str.length() <= 0 || str.length() > 9 || str.replaceAll("[a-zA-Z]", "").length() != 0){
            return list;
        }
        /*list.add(str);
        exchange(str.toCharArray(), 0, list);*/

        exchange(str, 0, list);

        return list;
    }

    private ArrayList<String> exchange(String str, int begin, ArrayList<String> list){
        return list;
    }

    private void exchange(char[] chars, int p, ArrayList<String> list){
        if (p >= chars.length - 1){
            return;
        }
        for (int i = p; i < chars.length; i++){
            if (chars[p] != chars[i]){
               swap(chars, p, i);
               if (!list.contains(new String(chars)))
                   list.add(new String(chars));
            }
            exchange(chars, p + 1, list);
            swap(chars, i, p);
        }
    }
    private void swap(char[] chars, int i1, int i2){
        char c = chars[i1];
        chars[i1] = chars[i2];
        chars[i2] = c;
    }

    public static void main(String[] args) {
        SortString sort = new SortString();
        ArrayList<String> lists = sort.Permutation("abc");

        for (String list : lists) {
            System.out.println(list);
        }
    }
}
