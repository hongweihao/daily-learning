package mkii.finger1;
import java.util.Stack;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * <p>
 * 测试：
 * head = null, head
 */
public class ReverseList {
    // 超时了，这里是改变指针的指向
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = stack.pop();
        ListNode current = newHead;
        while (!stack.empty()){
            current.next = stack.pop();
            current = current.next;
        }
        return newHead;
    }
    // 通过了，好像时间复杂度是一样的，这里是改变节点的值
    public ListNode reverselise2(ListNode head){
        if (head == null){
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode first = head;
        while (first != null) {
            stack.push(first.val);
            first = first.next;
        }
        first = head;
        while (!stack.empty()){
            first.val = stack.pop();
            first = first.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode1 = new ListNode(1);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ReverseList reverseLis = new ReverseList();
        ListNode result = reverseLis.reverselise2(listNode1);

        System.out.println(result.val);
        System.out.println(result.next.val);
        System.out.println(result.next.next.val);
    }
}
