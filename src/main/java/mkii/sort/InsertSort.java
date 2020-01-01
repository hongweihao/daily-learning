package mkii.sort;

/**
 * 插入排序
 * 将数据分为两部分，一部分已经排序好，另一部分待排序。（第一个默认已排序好）
 * 借助外部O(1)存储当前待排序的值，与已排序的值比较，不合适就移动位置。
 *
 *
 */
public class InsertSort {
    public int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public int[] insertSort(int[] array){
        if (array == null || array.length <= 0){
            return null;
        }
        int tmp;
        // 默认第一个是已排序好的
        for (int i = 1; i < array.length; i++){
            tmp = array[i];
            for (int j = i - 1; j >= 0; j--){
                if (tmp < array[j]){
                    // 移动到下一个位置，表示tmp在j的后面
                    array[j + 1] = array[j];
                    /*// 每次都把值放进去
                    array[j] = tmp;*/
                    // tmp最小
                    if (j == 0) {
                        array[j] = tmp;
                        break;
                    }
                }else {
                    array[j + 1] = tmp;
                    break;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {1,9,5,7,6,6,4,3,8,2,0};
        InsertSort insert = new InsertSort();
        int[] res = insert.insertionSort(a);
        int[] res2 = insert.insertSort(a);
        for (int re : res) {
            System.out.print(re);
        }
        System.out.println();
        for (int re : res2) {
            System.out.print(re);
        }
    }

}
