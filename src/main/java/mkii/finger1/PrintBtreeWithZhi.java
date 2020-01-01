package mkii.finger1;


import java.util.*;

/**
 * 请实现一个函数按照之字形打印二叉树，
 * 即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 *
 *
 *
 */
public class PrintBtreeWithZhi {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null){
            return arrayLists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(pRoot);

        while (!queue.isEmpty() || !stack.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
                //System.out.println(node.val);
                arrayList.add(node.val);
            }
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                //System.out.println(node.val);
                arrayList.add(node.val);
            }
            arrayLists.add(arrayList);
        }
        return arrayLists;
    }

    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null){
            return arrayLists;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(pRoot);

        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                if (node.right != null) {
                    queue2.add(node.right);
                }
                if (node.left != null) {
                    queue2.add(node.left);
                }
                //System.out.println(node.val);
                arrayList.add(node.val);
            }
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            while (!queue2.isEmpty()) {
                TreeNode node = queue2.poll();
                if (node.left != null) {
                    queue1.add(node.left);
                }
                if (node.right != null) {
                    queue1.add(node.right);
                }
                //System.out.println(node.val);
                arrayList2.add(node.val);
            }
            if (!arrayList.isEmpty()) {
                arrayLists.add(arrayList);
            }
            if (!arrayList2.isEmpty()) {
                arrayLists.add(arrayList2);
            }
        }
        return arrayLists;
    }

    public ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null){
            return arrayLists;
        }
        // 用两个栈存储每一行的节点
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(pRoot);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();

            // 奇数行，根节点在这一行
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                if (node.left != null) {
                    stack2.push(node.left);
                }
                if (node.right != null) {
                    stack2.push(node.right);
                }
                //System.out.println(node.val);
                arrayList.add(node.val);
            }
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            // 偶数行
            while (!stack2.isEmpty()) {
                TreeNode node = stack2.pop();
                if (node.right != null) {
                    stack1.push(node.right);
                }
                if (node.left != null) {
                    stack1.push(node.left);
                }
                //System.out.println(node.val);
                arrayList2.add(node.val);
            }
            // 如果只new一个List会有可能2行存储到一个List中
            if (!arrayList.isEmpty()) {
                arrayLists.add(arrayList);
            }
            if (!arrayList2.isEmpty()) {
                arrayLists.add(arrayList2);
            }
        }
        return arrayLists;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;

        PrintBtreeWithZhi printBtreeWithZhi = new PrintBtreeWithZhi();
        ArrayList<ArrayList<Integer>> arrayLists = printBtreeWithZhi.Print3(node1);

        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer integer : arrayList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

    }

}
