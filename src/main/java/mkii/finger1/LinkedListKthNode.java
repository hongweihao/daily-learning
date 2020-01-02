package mkii.finger1;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * 测试：
 * head = null, head = *p
 * k = 0, k = 1, k = 100(99)
 */
public class LinkedListKthNode {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0){
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        while (head != null){
            //stack.push(head);
            list.add(head);
            head = head.next;
        }
        // 超出范围
        if (k > list.size()){
            return null;
        }
        return list.get(list.size() - k);
    }

    public static void main(String[] args) {
        LinkedListKthNode listKthNode = new LinkedListKthNode();
    }
}

