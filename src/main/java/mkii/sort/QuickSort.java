package mkii.sort;

public class QuickSort {
    public static int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            quickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            quickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序算法——partition
     * 随机选择一个key，比key小的放在左边，比key大的放在右边
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        // Math.random() 生成0-1之间的随机数  Math.random() * A -> 生成小于A的一个随机数
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        // 先把key和end换位置，方便比较时候交换位置
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            // 寻找比key小的值，用smallIndex记录下来有几个，指向的是大于key的值
            if (array[i] <= array[end]) {
                smallIndex++;
                // i > smallIndex 表示左边有大于key的值，需要用当前的值(上面已经确定是小于等于key的了)换出来
                // i == smallIndex表示左边没有大于key的值，自然不需要替换
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3,3,2,1,5,1};
        for (int i = 0; i < 10; i++) {
            int[] b = QuickSort.quickSort(a, 0, a.length - 1);
            for (int val : b) {
                System.out.print(val);
                System.out.print(",");
            }
            System.out.println();
        }

    }
}
