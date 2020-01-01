package mkii.sort.self;

/**
 * 插入排序：将数组分为已排序区和未排序区，每次从未排序区中取出一个元素，
 * 然后在已排序区中找到此元素的位置，找位置的过程中涉及元素的移动
 *
 * 时间复杂度是O(n^2)
 * 空间复杂度O(1)
 *
 * 相对与冒泡排序：插入排序每次的比较只会有1此赋值操作，而冒泡每次比较有3次赋值操作(交换)
 *
 */
public class Insert {
    public void insertionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        for (int i = 1; i < a.length; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    // j+1表示待排序值的位置，可以移动过来，因为a[i]已经用value存储起来了
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            // 本来j+1就是i的位置，每次就--，break的时候j+1就是实际的位置了
            a[j+1] = value; // 插入数据
        }
    }

    public static void main(String[] args) {
        int[] a = {4,3 , 2, 1, 3, 3, 5};
        Insert insert = new Insert();
        insert.insertionSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
