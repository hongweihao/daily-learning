package mkii.finger1;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * 并不一定左右子树相同，也有可能是叶子交换
 *
 * 测试：
 * 1。null
 * 2.左右不对称
 * 3.左右对称
 * 4.左右不均衡
 */
public class SymmetricalBinaryTree {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null || ( pRoot.left == null && pRoot.right == null)){
            return true;
        }
        // 比较左右子树是否对称
        return compare(pRoot.left, pRoot.right);
    }

    private boolean compare(TreeNode left, TreeNode right){
        // 左右子树都空
        if (left == null && right == null){
            return true;
        }
        // 左右子树其中一个为空
        if (left == null || right == null){
            return false;
        }
        // 左右子树根节点值相同
        if (left.val == right.val){
            // 要求是对称的
            return compare(left.left, right.right) && compare(left.right, right.left);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode node = null;

        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        SymmetricalBinaryTree symmetricalBinaryTree = new SymmetricalBinaryTree();
        //System.out.println(symmetricalBinaryTree.isSymmetrical(node));
        System.out.println(symmetricalBinaryTree.isSymmetrical(node1));
    }
}
