package mkii.sort.self;

/**
 * 二分查找
 * 1. 序列有序且无重复元素， 需要查找唯一元素下标。simpleBinarySearch/simpleBinarySearchRecursionImplement
 * 2. 序列有序但是有重复元素，需要查找相同元素中最小下标。binarySearchGetMin
 * 3. 序列有序但是有重复元素，需要查找相同元素中最大下标。binarySearchGetMax
 * 4. 序列有序但是有/无重复元素，需要查找大于x元素的最小下标。
 * 5. 序列有序但是有/无重复元素，需要查找小于x元素的最大下标。
 *
 */
public class BinarySearch {
    /**
     * 简单二分查找循环实现，要求整数数组有序，且没有重复的元素
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int simpleBinarySearch(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }

        int low = 0;
        int high= a.length - 1;
        int mid;

        // 注意边界，如果序列只有两个数的情况下，mid等于左边数的索引，右边需要在下一轮进行比较
        while (low <= high){
            mid = (low + high) / 2;
            if (a[mid] == target){
                return mid;
            }else if (a[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }
    /**
     * 简单二分查找递归实现，要求整数数组有序，且没有重复的元素
     *
     * 递推公式：f(0-n, t) = f(0-n/2, t) if(a[mid] > t)
     *          f(0-n, t) = f(n/2-n, t) if(a[mid] < t)
     *
     * 终止条件：low > high (未找到)
     *          a[mid] == r (已找到)
     *
     */
    public int simpleBinarySearchRecursionImplement(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }
        int low = 0;
        int high= a.length - 1;

        return recursion(a, low, high, target);

    }
    private int recursion(int[] a, int low, int high, int target){
        // 未找到数据的递归终止条件
        if (low > high){
             return -1;
        }
        int mid = (low + high) >> 1;
        if (a[mid] == target){
            // 找到数据的终止条件
            return mid;
        }else if (a[mid] < target){
            return recursion(a, mid + 1, high, target);
        }else {
            return recursion(a, low, mid - 1, target);
        }
    }

    /**
     * 序列有序但是有重复元素，需要查找相同元素中最小下标。
     *
     * @param a
     * @param target
     * @return
     */
    public int binarySearchGetMin(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }

        int low = 0;
        int high= a.length - 1;
        int mid;

        while (low <= high){
            mid = (low + high) / 2;
            if (a[mid] == target){
                /*// 转换为顺序查找
                for (; mid >= low; mid--){
                    if (a[mid] < target){
                        return mid + 1;
                    }
                }*/
                // 只有前一个数不同时才算找到
                if (mid == 0 || a[mid - 1] != target){
                    return mid;
                }else {
                    high = mid - 1;
                }

            }else if (a[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 序列有序但是有重复元素，需要查找相同元素中最大下标。
     *
     * @param a
     * @param target
     * @return
     */
    public int binarySearchGetMax(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }

        int low = 0;
        int high= a.length - 1;
        int mid;

        while (low <= high){
            mid = (low + high) / 2;
            if (a[mid] == target){
                // 只有后一个数不同时才算找到
                if (mid == 0 || a[mid + 1] != target){
                    return mid;
                }else {
                    low = mid + 1;
                }

            }else if (a[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 序列有序但是有/无重复元素，需要查找大于等于x元素的最小下标。
     * @param a
     * @param target
     * @return
     */
    public int binarySearchGetMore(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }

        int low = 0;
        int high= a.length - 1;
        int mid;

        while (low <= high){
            mid = (low + high) / 2;
            if (a[mid] >= target && (mid == 0 || a[mid - 1] < target)){
                return mid;
            }else if (a[mid] < target){
                low = mid + 1;
            }else {
                // 这里不只有a[mid] > target这种情况
                // 也包含这种情况，a[mid] >= target && a[mid + 1] >= target..
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 序列有序但是有/无重复元素，需要查找小于等于x元素的最大下标。
     * @param a
     * @param target
     * @return
     */
    public int binarySearchGetLess(int[] a, int target){
        if (a == null || a.length <= 0){
            return -1;
        }

        int low = 0;
        int high= a.length - 1;
        int mid;

        while (low <= high){
            mid = (low + high) / 2;
            if (a[mid] <= target && (mid == 0 || a[mid + 1] > target)){
                return mid;
            }else if (a[mid] > target){
                high = mid - 1;
            }else {
                // 这里不只有a[mid] < target这种情况
                // 也包含这种情况，a[mid] <=target && a[mid + 1] <= target
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 4, 4, 4, 4, 4, 7, 9, 34};
        BinarySearch binarySearch = new BinarySearch();

        //System.out.println(binarySearch.simpleBinarySearch(a, 34));
        //System.out.println(binarySearch.simpleBinarySearchRecursionImplement(a, 34));

        int[] b = {1, 2, 3, 3, 3, 3, 3, 3, 5};
        //System.out.println(binarySearch.simpleBinarySearch(b, 3));
        //System.out.println(binarySearch.binarySearchGetMin(b, 3));
        //System.out.println(binarySearch.binarySearchGetMax(b, 3));

        int[] c = {1,2};
        System.out.println(binarySearch.binarySearchGetMore(c, 1));
        System.out.println(binarySearch.binarySearchGetLess(c, 1));
    }
}
