package mkii.leetcode.geekbang.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 测试：
 * 1. {}
 * 2. {-1, 0, 1, 2, -1, -4}  -> {-4, -1, -1, 0, 1, 2}
 * 3. {-1, 0, 6, 6, -1, -4}
 * 4. {0, 0, 0, 0}
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 0){
            return result;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {-1, 0, 1, 2, -1, -4};
        int[] nums3 = {-1, 0, 6, 6, -1, -4};
        int[] nums4 = {0, 0, 0, 0};

        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums4);

        System.out.println(lists.size());
    }
}
