##### 题目描述：
> 给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

##### 示例：
> 给定 `nums = [2, 7, 11, 15], target = 9`  
因为 `nums[0] + nums[1] = 2 + 7 = 9`  
所以返回 `[0, 1]` 


##### 我的思路：
> 使用两个`for`  
第一个`for`（索引为`i`）的范围是`0~nums.len-2`   
第二个`for`（索引为`j`）的范围是`i+1~len-1`
然后判断两数相加是否等于`target`  
若等于直接返回`i`和`j`

##### 标准解法：

###### 1.暴力法
```
public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] == target - nums[i]) {
                return new int[] { i, j };
            }
        }
    }
    throw new IllegalArgumentException("No two sum solution");
}
```
> 时间复杂度：`O(n^2)`,对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 `O(n)`的时间。因此时间复杂度为 `O(n^2)`。  

> 空间复杂度：`O(1)`。
###### 2.哈希表
```
public int[] twoSum(int[] nums, int target) {
       Map<Integer, Integer> map = new HashMap<>();
       for (int i = 0; i < nums.length; i++) {
           int complement = target - nums[i];
           if (map.containsKey(complement)) {
               return new int[] { map.get(complement), i };
           }
           map.put(nums[i], i);
       }
       throw new IllegalArgumentException("No two sum solution");
   }   
```
> 时间复杂度：`O(n)`， 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 `O(1)`的时间。

> 空间复杂度：`O(n)`， 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素
