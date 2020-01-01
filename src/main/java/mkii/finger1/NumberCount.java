package mkii.finger1;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 *
 */
public class NumberCount {
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length <= 0){
            return 0;
        }
        int right = getMaxIndex(array, k);
        int left = getMinIndex(array, k);
        if (left == -1 || right == -1){
            return 0;
        }else {
            return right - left + 1;
        }
    }

    private int getMinIndex(int[] array, int k){
        int p = 0;
        int q = array.length - 1;
        int mid;
        while (p <= q){
            mid = p + ((q - p) >> 2);
            if (array[mid] == k && (mid == 0 || array[mid-1] < k)){
                return mid;
            }else if (array[mid] < k){
                p = mid + 1;
            }else {
                q = mid - 1;
            }
        }
        return -1;
    }
    private int getMaxIndex(int[] array, int k){
        int p = 0;
        int q = array.length - 1;
        int mid;
        while (p <= q){
            mid = p + ((q - p) >> 2);
            if (array[mid] == k && (mid == array.length - 1 || array[mid + 1] > k)){
                return mid;
            }else if (array[mid] > k){
                q = mid - 1;
            }else {
                p = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 2, 3};
        int k = 1;
        NumberCount numberCount = new NumberCount();
        System.out.println(numberCount.GetNumberOfK(a, k));
    }
}
