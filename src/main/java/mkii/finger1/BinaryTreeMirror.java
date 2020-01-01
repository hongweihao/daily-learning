package mkii.finger1;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 *
 * 测试：
 * 1. null
 * 2. root
 * 3. 正常
 * 4. 一边
 * 5.一边再一边
 */
public class BinaryTreeMirror {
    public void Mirror(TreeNode root){
        root = swapChildren(root);
        System.out.println("ddon");
    }

    private TreeNode swapChildren(TreeNode root){
        if (root == null){
            return root;
        }
        // 有一个子树为空的情况
        if (root.left == null){
            root.left = root.right;
            root.right = null;
        }else if (root.right == null){
            root.right = root.left;
            root.left = null;
        }else {
            // 两个子树都不为空的情况，交换子树
            TreeNode temp;
            temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        // 交换子树之后，再交换子树的子树
        root.left = swapChildren(root.left);
        root.right = swapChildren(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(8);
        TreeNode treeNode2 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(11);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.left = treeNode4;
        treeNode4.left = treeNode5;

        BinaryTreeMirror binaryTreeMirror = new BinaryTreeMirror();
        binaryTreeMirror.Mirror(treeNode1);
    }
}
