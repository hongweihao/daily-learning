package mkii.finger1;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * 测试：
 * 1. null
 * 2.
 */
public class CloneLinked {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        Map<Integer, RandomListNode> nodes = new  HashMap<>();
        // clone list
        RandomListNode head = createNode(pHead, nodes);

        return head;
    }
    private RandomListNode createNode(RandomListNode head, Map<Integer, RandomListNode> nodes){
        RandomListNode node = null;
        // 递归出口
        if (head == null){
            return node;
        }
        // 处理label
        int label = head.label;
        if (nodes.containsKey(label)){
            // 节点已经存在
            node = nodes.get(label);
        }else {
            // 节点不存在需要重新new
            node = new RandomListNode(label);
        }
        // 处理random
        RandomListNode random = head.random;
        if (random != null) {
            if (nodes.containsKey(random.label)) {
                // 节点已经存在
                node.random = nodes.get(random.label);
            } else {
                // 节点不存在需要重新new
                node.random = new RandomListNode(random.label);
            }
        }
        //node.random = createNode(head.random, nodes);
        node.next = createNode(head.next, nodes);
        return node;
    }

    public static void main(String[] args) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node1.random = node3;
        node2.random = node3;
        node3.random = node1;
        node4.random = node2;

        CloneLinked cloneLinked = new CloneLinked();
        cloneLinked.Clone(node1);
    }
}
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}