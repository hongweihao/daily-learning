package mkii.sort;

import java.sql.SQLOutput;

/**
 * 希尔排序（缩小增量排序）
 *
 *
 * 偏移值增量缩小，分组
 *
 *
 */
public class ShellSort {
    public int[] shellSort(int[] array) {
        int len = array.length;
        int temp;
        // 增量的默认值是len的一半
        int gap = len / 2;
        while (gap > 0) {
            // 每对数值都要遍历比较
            for (int i = gap; i < len; i++) {
                // 最远（下标最大）的值
                temp = array[i];
                // 最近（下标最小）的值
                int preIndex = i - gap;
                // 如果最远值较小则把最近值放入最远值位置，保证最远值较大
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    // 下一对数值比较
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    public int[] shell(int[] a){
        if (a == null || a.length <= 0){
            return a;
        }
        int temp;
        for (int gap = a.length / 2; gap > 0; gap /= 2){
            for (int i = 0; i < a.length; i++){
                if (i + gap < a.length && a[i] > a[i + gap]){
                    temp = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = temp;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {1,9,5,7,6,6,4,3,8,2,0};
        ShellSort sort = new ShellSort();
        int [] res = sort.shell(a);
        for (int i : res){
            System.out.print(i);
        }
    }
}
