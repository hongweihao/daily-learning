package mkii.finger1;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
 *
 * 测试：
 * 1. 正常区间内 n=30
 * 2. n=39
 * 3. n=40
 * 4. n=0
 * 5. n=1
 */
public class Fibonacci {

    public int fibonacci(int n) {

        if (n < 0 || n > 39){
            System.out.println("out of limit");
        }

        if (n == 0){
            return 0;
        }

        if (n == 1){
            return 1;
        }

        int n_1 = 1;
        int n_2 = 0;
        int temp;

        for (int i = 2; i <= n; i++) {
            temp = n_1;
            n_1 = n_1 + n_2;
            n_2 = temp;
        }
        return n_1;
    }

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci();

        System.out.println(fibonacci.fibonacci(3));
    }
}
