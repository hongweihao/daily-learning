package mkii.finger1;

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *
 *
 */
public class ROL {
    // 需要2个额外的数组
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() <= n){
            return str;
        }
        String start = str.substring(0, n);
        String end = str.substring(n);
        return end.concat(start);
    }
    // 只需要一个额外的数组
    public String leftRotateString(String str, int n){
        if (str == null || str.length() <= n){
            return str;
        }
        int p = 0;
        char[] chars = new char[str.length()];

        for (int i = n; i < str.length(); i++,p++){
            chars[p] = str.charAt(i);
        }

        for (int i = 0; i < n; i++,p++){
            chars[p] = str.charAt(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "abcXYZdef";
        int n = 3;

        ROL rol = new ROL();
        String s = rol.LeftRotateString(str, n);
        String s1 = rol.leftRotateString(str, n);

        System.out.println(s);
        System.out.println(s1);
    }
}
