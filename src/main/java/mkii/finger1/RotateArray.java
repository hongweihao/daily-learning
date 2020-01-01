package mkii.finger1;

import sun.security.util.Length;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 * 测试：
 * 1. null
 * 2. 空数组
 * 3. 正常旋转数组
 * 4. 非旋转数组
 * 5. 特殊旋转数组{2,2,2, 1, 2,2,2,2}
 */
public class RotateArray {

    public int minNumberInRotateArray(int[] array) {

        if (array == null || array.length <= 0) {
            System.out.println("array is null");
            return 0;
        }

        if (array[0] == array[array.length - 1] && array[0] == array[(array.length - 1) / 2]) {
            return minInSpecieArray(array);
        }

        return minInRotateArray(array);
    }

    private int minInRotateArray(int[] array) {
        int p = 0;
        int q = array.length - 1;
        int key;

        while (p + 1 < q) {
            key = (p + q) / 2;
            if (array[key] >= array[p]) {
                p = key;
            }
            if (array[key] <= array[q]) {
                q = key;
            }
        }
        return array[q];
    }

    private int minInSpecieArray(int[] array) {

        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 5, 1, 2};
        int[] b = {2, 2, 2, 1, 2, 2, 2, 2};
        int[] c = null;

        RotateArray rotateArray = new RotateArray();

        System.out.println(rotateArray.minInRotateArray(a));
        System.out.println(rotateArray.minInSpecieArray(b));

        System.out.println(rotateArray.minNumberInRotateArray(a));
        System.out.println(rotateArray.minNumberInRotateArray(b));
        System.out.println(rotateArray.minNumberInRotateArray(c));
    }
}
