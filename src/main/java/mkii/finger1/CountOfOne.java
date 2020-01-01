package mkii.finger1;

/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class CountOfOne {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 1) {
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(i);
        }
        int len = builder.length();
        int len2 = builder.toString().replaceAll("1", "").length();
        return len - len2;
    }

    public int num(int n) {
        if (n < 1) {
            return 0;
        }
        int sum = 1;
        for (int i = 10; i <= n; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    sum++;
                }
            }
        }
        return sum;
    }

    public int num2(int n) {
        if (n < 1) {
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 10; i <= n; i++) {
            builder.append(i);
        }
        int sum = 1;
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '1') {
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        CountOfOne countOfOne = new CountOfOne();
        System.out.println(countOfOne.NumberOf1Between1AndN_Solution(130));
        System.out.println(countOfOne.num2(130));
    }
}
