package mkii.finger1;

import java.util.ArrayList;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 *
 */
public class AppearOne {
    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length <= 0) {
            return;
        }
        // 排序
        quickSort(array, 0, array.length - 1);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1 || array[i] != array[i + 1]){
                arrayList.add(array[i]);
            }else {
                i++;
            }
        }
        num1[0] = arrayList.get(0);
        num2[0] = arrayList.get(1);
    }

    private void quickSort(int[] array, int low, int high){
        int pivot = partition(array, low, high);
        if (low < pivot - 1){
            quickSort(array, low, pivot - 1);
        }
        if (high > pivot + 1) {
            quickSort(array, pivot + 1, high);
        }
    }
    private int partition(int[] array, int low, int high) {
        // key是array[high]
        int smallIndex = low - 1;
        // 遍历区间的值，分区
        for (int i = low; i <= high; i++) {
            if (array[i] <= array[high]) {
                smallIndex++;
                // 如果左边没有大于key的值存在，那smallIndex和i相同
                if (i > smallIndex) {
                    int temp = array[i];
                    array[i] = array[smallIndex];
                    array[smallIndex] = temp;
                }
            }
        }
        return smallIndex;
    }

    public static void main(String[] args) {
        int[] array = {2,4,3,6,3,2,5,5};

        int[] a = new int[1];
        int[] b = new int[1];
        AppearOne appearOne = new AppearOne();
        appearOne.FindNumsAppearOnce(array, a, b);

        System.out.println(a[0]);
        System.out.println(b[0]);
    }
}
