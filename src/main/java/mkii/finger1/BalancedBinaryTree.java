package mkii.finger1;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 平衡二叉树：左右子树高度不超过1
 *
 * 判断是否平衡二叉树：空树，左/右子树是平衡二叉树，且左右子树的深度差不超过1
 */
public class BalancedBinaryTree {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 先判断左右子树是否是平衡二叉树
        if (IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right)){
            // 再判断左右子树的高度差
            int res = Math.abs(getDeep(root.left, 0) - getDeep(root.right, 0));
            if (res == 0 || res == 1){
                return true;
            }
        }
        return false;
    }
    private int getDeep(TreeNode root, int deep){
        // 空节点不计深度
        if (root == null){
            return deep;
        }
        // 叶子节点需要计深度
        deep++;
        int a = deep;
        int b = deep;
        if (root.left != null) {
            a = getDeep(root.left, deep);
        }
        if (root.right != null) {
            b = getDeep(root.right, deep);
        }
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;

        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        boolean flag = balancedBinaryTree.IsBalanced_Solution(node1);
        System.out.println(flag);
    }

    /*
    {1,2,3,4,5,#,6,#,#,7}

     */

}
