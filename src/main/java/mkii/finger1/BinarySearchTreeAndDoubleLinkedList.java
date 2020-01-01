package mkii.finger1;

/**
 * ？？？ 还需要时间理解
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 总体思路复盘：分为左子树/根/右子树。另外需要一个指针指向已排序的最后一个节点
 * 先对左子树进行转换，然后根作为左子树的最后（最大）一个节点。
 * 然后对根的右子树转换。
 *
 *
 * 测试：
 *
 */
public class BinarySearchTreeAndDoubleLinkedList {
    // 指向转换好的链表的最后一个节点
    private TreeNode lastNode;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        convertNode(pRootOfTree);

        // 取得双向链表的头节点
        TreeNode headNode = lastNode;
        while (headNode != null && headNode.left != null){
            headNode = headNode.left;
        }
        return headNode;
    }

    private void convertNode(TreeNode node){
        if (node == null) {
            return;
        }
        // 当前节点
        TreeNode current = node;
        // 左子树转换
        if (current.left != null){
            convertNode(current.left);
        }
        // 当前节点的左指针需要指向左子树的最后一个节点
        current.left = lastNode;

        // 当前节点作为左子树链表的最大节点
        if (lastNode != null){
            lastNode.right = current;
        }
        lastNode = current;

        // 左子树转换完就转换右子树
        if (current.right != null){
            convertNode(current.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(14);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(16);
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.left = node6;
        node5.right = node7;

        BinarySearchTreeAndDoubleLinkedList binarySearchTreeAndDoubleLinkedList = new BinarySearchTreeAndDoubleLinkedList();
        TreeNode head = binarySearchTreeAndDoubleLinkedList.Convert(node1);
        System.out.println(head.val);
    }
}
