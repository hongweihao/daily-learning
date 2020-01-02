package mkii.finger1;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * <p>
 * 测试：
 * 1.{1, 2, 3, 4, 5}  {2, 4, 5}
 * 2.{1, 2, 3, 4, 5}  {3, 4, 6}
 * 3.{1, 2, 3, 4, 5}  {3, 4, 6}
 * 3.{1, 2, 3, 4, 5}  {3, 4, 5}
 * 4. null || null
 */
public class HasSubTree {
    // A的当前节点能否匹配到B的根节点，如果能匹配到则继续比较子节点；不能匹配到则递归判断子树能否匹配到
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean flag = false;
        // 深入查找
        if (root1.val == root2.val) {
            flag = hasNode(root1, root2);
        }
        // 左子树查找，如果root的值不匹配需要在子树中查找
        if (!flag) {
            flag = hasSubtree(root1.left, root2);
        }
        // 右子树查找
        if (!flag) {
            flag = hasSubtree(root1.right, root2);
        }
        return flag;
    }

    // 判断两个树是否相同，root已经是相等了的
    private boolean hasNode(TreeNode root1, TreeNode root2) {
        // 递归出口，B树全部遍历完
        if (root2 == null) {
            return true;
        }
        // 保证边界值（叶子）
        if (root1 != null) {
            if (root1.val != root2.val) {
                return false;
            }
            // 左右子树都需要通过才行
            return hasNode(root1.left, root2.left) && hasNode(root1.right, root2.right);
        }
        // A树先到达边界
        return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode44 = new TreeNode(4);
        TreeNode treeNode55 = new TreeNode(5);
        treeNode22.left = treeNode44;
        treeNode22.right = treeNode55;

        HasSubTree hasSubTree = new HasSubTree();
        System.out.println(hasSubTree.hasSubtree(treeNode1, treeNode22));
    }
}
