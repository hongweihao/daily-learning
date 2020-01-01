package mkii.sort.self;

/**
 * 归并排序：利用分治思想，将1个序列分为2个，2个分为4个一直分直到每个元素都是1个序列。然后11合并为2，22/并为21合4/3.。
 * 合并的过程才是真正的排序过程，省时的原因是合并两个有序序列，如果其中一个序列移动完成，另一个序列直接迁移就行了，不需要再比较。
 * 时间复杂度：O(nlogn)  T(n) = T(p,mid) + T(mid, q) + K(合并时间)
 * 空间复杂度：O(n), 虽然每次都会申请临时空间，但是每次使用完之后都会回收，取最大的是，当该序列分为两个序列时，需要n大小的临时空间
 * 稳定算法, 在merge中可以限制为稳定
 * 缺点, 非原地排序算法，需要n大小的临时空间
 *
 * 递归分析：
 * 1.递推公式：mergeSort(p, q) = mergeSort(p, mid) + megeSort(mid+1, r)
 * 2.终止条件：p >= q（表示已经分割到最小粒度1个了）
 */
public class Merge {

    public void mergeSort(int[] a) {
        if (a == null || a.length <= 0){
            return;
        }
        mergeSort(a, 0, a.length - 1);
    }

    private void mergeSort(int[] a, int p, int q) {
        // 递归终止条件，指针指向同一个元素
        if (p >= q) return;
        // 递推公式
        int mid = (p + q) >> 1;
        mergeSort(a, p, mid);
        mergeSort(a, mid + 1, q);

        // 合并两个排序好的序列
        merge(a, p, mid, mid+1, q);
    }

    // 合并两个排序序列，这里是真正排序的实现
    private void merge(int[] a, int p1, int q1, int p2, int q2) {
        int[] temp = new int[q2-p1+1];
        int head = p1;
        int tempPoint = 0;
        // 比较移动，排序实现
        while (p1 <= q1 && p2 <= q2){
            if (a[p1] <= a[p2]){
                temp[tempPoint++] = a[p1++];
            }else{
                temp[tempPoint++] = a[p2++];
            }
        }
        // 没移动完成的部分
        while (p1 <= q1){
            temp[tempPoint++] = a[p1++];
        }
        while (p2 <= q2){
            temp[tempPoint++] = a[p2++];
        }
        // 移动到源数组
        for (int value : temp) {
            a[head++] = value;
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 2, 1, 3};
        Merge merge = new Merge();
        merge.mergeSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
