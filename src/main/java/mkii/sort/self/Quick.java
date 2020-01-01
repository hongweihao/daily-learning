package mkii.sort.self;

/**
 * 快速排序：在一个数组中选择一个key，把小于key的放左边其他的放右边。
 * <p>
 * 时间复杂度：平均O(nlogn)，最坏逆序O(n^2)
 * 空间复杂度：O(1)
 * 不稳定算法
 * <p>
 * 递归分析：
 * 1.递推公式：quickSort(p, q) = quickSort(p, pivot-1) + quickSort(pivot+1, q)
 * 2.终止条件：p>=pivot-1, pivot+1>=q,表示分区中有1个或者0个元素
 */
public class Quick {
    public void quickSort(int[] a) {
        if (a == null || a.length <= 0) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 递推公式的实现
     *
     * @param a 数组
     * @param p 左边界
     * @param q 右边界
     */
    private void quickSort(int[] a, int p, int q) {
        // 得到哨兵的位置
        int pivot = partition(a, p, q);
        // 终止条件，表示该分区只有1个或者0个元素
        if (p < pivot - 1) {
            quickSort(a, p, pivot - 1);
        }
        // 终止条件，表示该分区只有1个或者0个元素
        if (pivot + 1 < q) {
            quickSort(a, pivot + 1, q);
        }
    }

    /**
     * 分区，小于等于哨兵的放左边，其他放右边
     * 返回的是哨兵的位置
     *
     * @param a 数组
     * @param p 左边界
     * @param q 右边界
     * @return 哨兵的位置
     */
    private int partition(int[] a, int p, int q) {
        int pivot = (int) (p + Math.random() * (q - p + 1));
        swap(a, pivot, q);
        // 上面两步可以不要，那么就是默认最后一个元素是哨兵
        int smallIndex = p - 1;
        for (int i = p; i <= q; i++) {
            if (a[i] <= a[q]) {
                // 只有找到小于等于key的值smallIndex才会+1，最后一个是key本身，所以最后的位置就是分割的位置
                smallIndex++;
                // 如果左边没有大于key的值，那么smallIndex=i
                // 如果左边有大于key的值，那么smallIndex<i
                if (smallIndex < i) {
                    // 上n轮找到的是大于key的值，所以smallIndex并没有自增，指向第一个大于key的值，本轮找到了一个小于key的值在i位置
                    swap(a, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 11, 3, 51, 4, 9};
        Quick quick = new Quick();
        quick.quickSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
