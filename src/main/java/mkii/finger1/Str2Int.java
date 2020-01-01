package mkii.finger1;

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 */
public class Str2Int {
    public int StrToInt(String str) {
        if (!valid(str)) {
            return 0;
        }
        int sum = 0;
        int qualify = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            int res = str.charAt(i) - '0';
            if ((res > 9 || res < 0) && c != '-' && c != '+') {
                return 0;
            }
            if (res == 0) {
                continue;
            }
            // 有可能是负数
            if (res >= 0 && res <= 9){
                sum += res * qualify;
            }else if ( c == '-'){
                sum = sum * -1;
            }
            qualify *= 10;
        }
        return sum;
    }
    private boolean valid(String str){
        if (str == null || "".equals(str) || "0".equals(str)){
            return false;
        }
        String max = "2147483647";
        String min = "2147483648";
        if (str.charAt(0) == '-'){
            return str.substring(1).compareTo(min) <= 0;
        }else {
            return str.compareTo(max) <= 0;
        }
    }

    public static void main(String[] args) {
        Str2Int str2Int = new Str2Int();
        int res = str2Int.StrToInt("-2147483647");
        System.out.println(res);


       /* System.out.println("123".compareTo("2147483647"));
        System.out.println("123".compareTo("-2147483648"));*/
        // || str.compareTo("2147483647") > 0 || str.compareTo("-2147483648") > 0

    }
}
