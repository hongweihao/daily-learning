package mkii.finger1;

import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 二叉搜索树（Binary Search Tree）又称二叉排序树（Binary Sort Tree），
 * 需要保证根节点的左子树元素皆小于根，右子树皆大于根。
 * 中序遍历序列是一个排序完成的序列
 *
 * 跟前边的重建二叉树类似
 *
 *
 * 测试：
 * 1. 正常序列
 * 2. 错误序列
 * 3. null | length = 0
 * 4. 1
 * 5. 只有左树
 * 6. 只有右树
 */
public class BinarySearchTree {
    // 后序序列指针，如果不设置全局，一旦递归两层就会丢失递归函数中的操作，（--p不生效了，p的位置就混乱了）
    private int p;

    public boolean VerifySequenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length <= 0){
            return false;
        }

        // 得到中序遍历序列
        int[] mid = Arrays.copyOf(sequence, sequence.length);
        Arrays.sort(mid);
        // 初始化指针后往前，因为是后序序列
        p = sequence.length - 1;
        return eachBinarySearchTree(mid, 0, mid.length - 1, sequence);
    }

    // 根据中/后序序列，生成一颗二叉搜索树
    private boolean eachBinarySearchTree(int[] mid, int low, int high, int[] after/*, int p*/){
        // 找到根在中序序列中的位置
        int rootIndex = -1;
        for (int i = low; i <= high; i++) {
            if (mid[i] == after[p]){
                rootIndex = i;
                break;
            }
        }
        // 判断根节点是否存在
        if (rootIndex == -1){
            return false;
        }
        // 子树
        boolean flag = true;
        if (rootIndex + 1 <= high) {
            --p;
            flag = eachBinarySearchTree(mid, rootIndex + 1, high, after/*, --p*/);
        }
        if (low <= rootIndex - 1) {
            --p;
            flag = flag && eachBinarySearchTree(mid, low, rootIndex - 1, after/*, --p*/);
        }
        return flag;
    }

    public static void main(String[] args) {
        int[] after = {2,3,1};
        BinarySearchTree searchTree = new BinarySearchTree();
        System.out.println(searchTree.VerifySequenceOfBST(after));
    }
}
