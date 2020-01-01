package mkii.finger1;

import java.util.*;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p>
 * 测试：
 * 1. null
 * 2. 完全二叉树
 * 3. 一般二叉树
 * 4. 左一半二叉树
 * 5. 右一半二叉树
 * 6. 只有根节点
 *
 */
public class PrintBinaryTree {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 打印输出的结点值
        ArrayList<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        // 使用队列按顺序暂时存放每一层的结点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currentNode = null;

        while (!queue.isEmpty()) {
            // 获取队列头
            currentNode = queue.remove();
            // 将队列头的结点输出
            nodes.add(currentNode.val);
            // 将队列头的子节点放到队列尾
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return nodes;
    }

    // newcode题目
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        // 打印输出的结点值
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        if (pRoot == null) {
            return nodes;
        }
        // 使用队列按顺序暂时存放每一层的结点
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(pRoot);
        TreeNode currentNode = null;

        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            Queue<TreeNode> queue, emptyQueue;
            if (queue1.isEmpty()){
                queue = queue2;
                emptyQueue = queue1;
            }else {
                queue = queue1;
                emptyQueue = queue2;
            }
            ArrayList<Integer> node = new ArrayList<>();
            while (!queue.isEmpty()) {
                // 获取队列头
                currentNode = queue.remove();
                // 将队列头的结点输出
                node.add(currentNode.val);
                // 将队列头的子节点放到队列尾
                if (currentNode.left != null) {
                    emptyQueue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    emptyQueue.add(currentNode.right);
                }
            }
            nodes.add(node);
        }
        return nodes;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;


        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
        //ArrayList<Integer> result = printBinaryTree.PrintFromTopToBottom(treeNode1);
        ArrayList<ArrayList<Integer>> nodes = printBinaryTree.Print(treeNode1);

        for (ArrayList<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
