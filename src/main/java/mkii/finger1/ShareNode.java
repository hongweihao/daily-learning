package mkii.finger1;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 *
 */
public class ShareNode {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        ListNode p = pHead1;
        ListNode q;

        while (p != null) {
            q = pHead2;
            while (q != null) {
                if (p == q) {
                    return p;
                }
                q = q.next;
            }
            p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
