package mkii.finger1;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 思路：将每一个节点都看作一个根节点（包括叶子节点），遍历前序序列匹配中序序列可以得到每一个“根”节点
 *
 *
 * 测试：
 * 1. 两个正确的序列
 * 2. 一个错误的前序序列，正确的中序序列
 * 3. 两个错误的序列
 * 4. 一个空前序序列
 * 5. 一个空中序序列
 * 6. 两个空序列
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class RebuildTree {
    // 前序序列的指针（确定根）
    private int prePoint = 0;

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0) {
            return null;
        }
        TreeNode root = getNode(pre, in, 0, in.length - 1);
        return root;
    }

    private TreeNode getNode(int[] pre, int[] in, int inStart, int inEnd) {
        // 确定根节点在中序序列中的位置
        int rootIndex = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[prePoint]) {
                rootIndex = i;
            }
        }
        if (rootIndex == -1) {
            return null;
        }
        // 确定根节点，如果是叶子节点，就不会继续create左右节点
        TreeNode root = new TreeNode(pre[prePoint]);

        //左子树
        if (inStart <= rootIndex - 1) {
            prePoint++;
            root.left = getNode(pre, in, inStart, rootIndex - 1);
        }
        if (inEnd >= rootIndex + 1) {
            prePoint++;
            root.right = getNode(pre, in, rootIndex + 1, inEnd);
        }
        return root;
    }

    public void inEach(TreeNode treeNode){

        if (treeNode == null){
            return;
        }
        inEach(treeNode.left);
        System.out.println(treeNode.val);
        inEach(treeNode.right);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        RebuildTree rebuildTree = new RebuildTree();
        TreeNode tree = rebuildTree.reConstructBinaryTree(pre, in);
        rebuildTree.inEach(tree);

        /*System.out.println(tree.val);
        System.out.println(tree.left.val);
        System.out.println(tree.right.val);*/
    }
}
