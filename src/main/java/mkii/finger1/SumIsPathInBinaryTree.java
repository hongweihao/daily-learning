package mkii.finger1;

import java.util.ArrayList;
/**
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 *
 * 测试：
 *
 */
public class SumIsPathInBinaryTree {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();
        int sum = 0;
        find(root, target, sum, path, result);
        return result;
    }
    private ArrayList<ArrayList<Integer>> find(TreeNode root, int target, int current, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result){
        // 将路径数值放入路径中
        path.add(root.val);
        current += root.val;

        // 到达叶子节点且路径符合，就收集起来
        boolean isLeaf = root.left == null && root.right == null;
        if (target == current && isLeaf){
            System.out.print("found a path: ");
            for (Integer i : path) {
                System.out.print(i);
            }
            System.out.println();
            result.add(new ArrayList<Integer>(path));
        }
        // 不是叶子节点就继续遍历子节点
        if (root.left != null){
            find(root.left, target, current, path, result);
        }
        if (root.right != null){
            find(root.right, target, current, path, result);
        }
        // 返回父节点，删除路径中的当前节点
        path.remove(path.size() - 1);
        return result;
    }

    public static void main(String[] args) {
        int target = 22;
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        SumIsPathInBinaryTree sumIsPathInBinaryTree = new SumIsPathInBinaryTree();
        ArrayList<ArrayList<Integer>> result = sumIsPathInBinaryTree.FindPath(node1, target);

        System.out.println(node3.val);
    }
}
