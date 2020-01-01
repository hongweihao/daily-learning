package mkii.datastru;

public class Heap {
    // 用数组来存储堆，因为堆是完全二叉树，利用数组存储相对来说能够节省空间
    private int[] a; // 数组，从下标1开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    // 向堆中插入数据
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        // 把数据插入到堆的最后一个位置
        ++count;
        a[count] = data;
        int i = count;

        // i/2 > 0 表示不是根节点
        // a[i] > a[i/2]表示子节点大于父节点，交换，说明是最大堆
        while (i/2 > 0 && a[i] > a[i/2]) { // 自下往上堆化
            swap(a, i, i/2); // swap()函数作用：交换下标为i和i/2的两个元素
            i = i/2;
        }
    }
    // 删除根节点
    public void removeMax() {
        if (count == 0) return ; // 堆中没有数据
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    // 自上往下堆化,
    private void heapify(int[] a, int n, int i) {

        while (true) {
            // 从上往下堆化的指针
            int maxPos = i;

            // 非叶子节点且父节点小于左子节点
            if (i*2 <= n && a[i] < a[i*2]) maxPos = i*2;

            // 非叶子节点且父节点小于右子节点
            if (i*2+1 <= n && a[maxPos] < a[i*2+1]) maxPos = i*2+1;

            // 不需要与子节点交换的情况下就是堆化完成了
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }

    }

    private void buildHeap(int[] a, int n) {
        // 从叶子节点的上一层开始，从右到左再从下到上堆化
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    // n表示数据的个数，数组a中的数据从下标1到n的位置。
    public void sort(int[] a, int n) {
        // 构建堆
        buildHeap(a, n);
        // 排序
        int k = n;
        while (k > 1) {
            // 堆化后最大的节点应该是根节点，所以把根节点放到最后一个位置（已排序区）
            // 再从从k-1个节点（未排序区）中选择一个最大的值（堆化），根节点就是最大的节点
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        int[] a = {8, 2, 3, 1, 4, 5, 6, 7};
        Heap heap = new Heap(7);
        heap.sort(a, 6);

        for (int i : a) {
            System.out.println(i);
        }

    }
}
