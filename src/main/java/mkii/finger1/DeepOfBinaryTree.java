package mkii.finger1;

/**
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class DeepOfBinaryTree {
    private int max;

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int deepin = 1;
        each(root, deepin);
        return max;
    }

    private void each(TreeNode root, int deepin) {
        // 叶子节点
        if (root.left == null && root.right == null) {
            if (deepin > max) {
                max = deepin;
            }
            return;
        }
        if (root.left != null) {
            each(root.left, deepin + 1);
        }
        if (root.right != null) {
            each(root.right, deepin + 1);
        }
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
        node3.left = node6;
        node6.right = node7;

        DeepOfBinaryTree deep = new DeepOfBinaryTree();
        int deepin = deep.TreeDepth(node1);
        System.out.println(deepin);
    }

    /*
    {1,2,3,4,5,#,6,#,#,7}


     */
}
