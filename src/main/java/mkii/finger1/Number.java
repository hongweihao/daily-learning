package mkii.finger1;

import com.sun.org.apache.regexp.internal.REUtil;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * & 与，只有11才是1
 * | 或，只有00才是0
 * ^ 异或，相同为0，不同为1
 *
 * <p>
 * 测试：
 * 1. 0
 * 2. 正数
 * 3. 负数
 */
public class Number {

    /**
     * 作弊的解法(java API)
     *
     * @param n
     * @return
     */
    public int NumberOf0(int n) {
        return Integer.bitCount(n);
    }

    /**
     * 对于正数来说，这个方法是正确的，因为正数右移是在左侧添加0
     * 而对于负数来说，右移操作是在左侧添加1，会发生死循环
     * @param n
     * @return
     */
    public int NumberOf1(int n){

        int count = 0;
        while (n != 0){
            if ((n & 1) == 1){ // 如果最后一位是1
                count++;
            }
            n = n >> 1;  //把最后一位丢弃
        }
        return count;
    }

    /**
     * 对每个
     * @param n
     * @return
     */
    public int NumberOf2(int n){
        int count = 0;
        int flag = 1;
        // 为什么flag>0不行？因为int的范围会导致最后一次左移溢出为负数，每次左移都会进一位，最后超出限制了为0
        while (flag != 0){
            // n & flag 都是0的时候才会满足==0，但是flag可能是正数或者负数但是不可能是0
            // 为什么>0不行？ 最后一次flag溢出时，flag是负数，如果n是正数，那么就会有<0的情况出现，就会漏掉
            if ((n & flag) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 每个数-1再与自己&可以得到少一个1的数
     * @param n
     * @return
     */
    public int NumberOf3(int n){
        int count = 0;
        while (n != 0){
            n = n & (n - 1);
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.NumberOf3(5));
        System.out.println(number.NumberOf0(5));
    }
}
