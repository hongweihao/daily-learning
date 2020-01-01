package mkii.finger1;

import java.math.BigInteger;

/**
 * 打印1到n位十进制数
 * (竟然需要考虑大数问题) 可以用java的api BigInteger
 *
 * 测试：
 * 0,-1,1,5
 *
 */
public class PrintNumber {

    /**
     * 不满足大数，int可能会溢出，long也可能会溢出
     * @param n
     */
    public void print(int n){

        if (n < 1){
            return;
        }

        int sum = 0;
        while (n-- > 0){
            sum += 9 * Math.pow(10, n);
        }

        for (int i = 1; i <= sum; i++){
            System.out.println(i);
        }
    }

    public void print2(int n){

        if (n < 1){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (n-- > 0){
            stringBuilder.append("9");
        }
        BigInteger bigInteger = new BigInteger(stringBuilder.toString());
        BigInteger bigInteger1 = new BigInteger("1");
        BigInteger val = new BigInteger("1");

        while (bigInteger.compareTo(bigInteger1) >= 0){
            System.out.println(bigInteger1);
            bigInteger1 = bigInteger1.add(val);
        }
    }

    public void print3(int n){

    }

    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        printNumber.print2(10);
    }
}
