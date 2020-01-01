package mkii.finger1;
/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 测试：
 * 1.
 */
public class ArrayBeMin {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length <= 0)
            return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (compare(numbers[i], numbers[j]) > 0){
                    swap(numbers, i, j);
                }
            }
            builder.append(numbers[i]);
        }
        return builder.toString();
    }

    /**
     * 定义一个排序规则，如果ab > ba 则a > b
     * @param a
     * @param b
     * @return 1(a > b)  0(a = b) -1(a < b)
     */
    private int compare(int a, int b){
        StringBuilder abuilder = new StringBuilder();
        StringBuilder bbuilder = new StringBuilder();
        abuilder.append(a).append(b);
        bbuilder.append(b).append(a);
        /*System.out.print(abuilder.toString());
        System.out.print("--");
        System.out.println(bbuilder.toString());*/
        return abuilder.toString().compareTo(bbuilder.toString());
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] numbers = {32,3,321};
        ArrayBeMin arrayBeMin = new ArrayBeMin();
        System.out.println(arrayBeMin.PrintMinNumber(numbers));

            /*int[] a = arrayBeMin.quick(numbers, 0, numbers.length-1);
            for (int val : a) {
                System.out.print(val);
                System.out.print(",");
            }
            System.out.println();*/


    }
}
