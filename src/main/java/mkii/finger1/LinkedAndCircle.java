package mkii.finger1;

import java.util.HashMap;
import java.util.Map;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 *
 */
public class LinkedAndCircle {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        // 用map存已经遍历的节点
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode current = pHead;

        // 遍历，直到遇到空或者重复
        while (current != null && !map.containsKey(current)){
            map.put(current, 1);
            current = current.next;
        }
        // 遍历查找完当前节点是空或者重复节点
        return current;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node1;

        /*node2.next = node3;
        node3.next = node4;
        node4.next = node5;*/
        //node5.next = node3;

        LinkedAndCircle linkedAndCircle = new LinkedAndCircle();
        ListNode listNode = linkedAndCircle.EntryNodeOfLoop(node1);
        System.out.println(listNode.val);
    }
}
