package mkii.finger1;

import java.util.Arrays;

/**
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,
 * 如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
 * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
 * LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 *
 */
public class Continuous {
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length != 5){
            return false;
        }
        // O(nlogn)
        Arrays.sort(numbers);

        // O(n)
        // 检查是否存在A，因为A可能是1也可能是14
        for (int i = 0; i < numbers.length - 1; i++){
            if (numbers[i] == 1){
                // 如果是大顺，要把A的值换成14，并移动数据
                if (numbers[i + 1] > 9){
                    for (int j = i; j < numbers.length - 1; j++) {
                        numbers[j] = numbers[j + 1];
                    }
                    numbers[numbers.length - 1] = 14;
                    break;
                }
            }
        }
        // O(n)
        int countOfZero = 0;
        for (int i = 0; i < numbers.length - 1; i++){
            // 王
            if (numbers[i] == 0){
                countOfZero++;
            }else{
                // 除了0之外的，检查下一个是否合法
                int res = numbers[i + 1] - numbers[i] -1;
                // 如果和下一个的差距超过王（0）的个数，或者有对子
                if (countOfZero < res || res == -1){
                    return false;
                }else {
                    countOfZero -= res;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {0, 0, 10, 12, 13};
        int[] c = {2, 4, 10, 12, 13};
        int[] d = {0, 9, 11, 12, 13};
        int[] e = {0, 2, 11, 12, 13};
        int[] f = {1,0,0,1,0};
        Continuous continuous = new Continuous();
        System.out.println(continuous.isContinuous(f));

    }
}
