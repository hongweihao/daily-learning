package mkii.finger1;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。 （稳定算法）
 *
 * 测试：
 * 1.null []
 * 2.{1,2,3,4,5,6}
 * 3.{1,3,5,7,9}
 * 3.{2,4,6}
 * 4.{0}
 * 5.{1,3,5,2,4,6}
 */
public class ReOrderArray {

    /**
     * 用O(n)空间的新数组存储
     * @param array
     */
    public void reOrderArray(int [] array) {
        if (array == null || array.length <= 0){
            return;
        }
        if (array.length == 1){
            //return array;
            return;
        }

        int[] newArray = new int[array.length];
        int newArrayP = 0;
        int arrayP = 0;
        while (arrayP < array.length){
            if (array[arrayP] % 2 == 1){
                newArray[newArrayP++] = array[arrayP];
            }
            arrayP++;
        }
        arrayP = 0;
        while (arrayP < array.length){
            if (array[arrayP] % 2 == 0){
                newArray[newArrayP++] = array[arrayP];
            }
            arrayP++;
        }

        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }


        array = newArray;
        System.out.println("done~");
    }

    public static void main(String[] args) {

        int[] a = {1,2,3,4,5,6,7};
        /*int[] b = {1,3,5,7,9};
        int[] c = {0};
        int[] d = {1,3,5,2,4,6};*/

        ReOrderArray reOrderArray = new ReOrderArray();
        reOrderArray.reOrderArray(a);
        /*reOrderArray.reOrderArray(b);
        reOrderArray.reOrderArray(c);
        reOrderArray.reOrderArray(d);*/
    }

}
