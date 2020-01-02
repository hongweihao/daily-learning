package mkii.finger1;


/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 * 测试：
 * 1.null/只有一个节点，返回源
 * 2. 无重复
 * 3. 2个重复
 * 4. 5个重复
 * 5. 多次重复
 * 5. 多次全部重复
 *
 */
public class RemoveDuplication {
    // 这是去重的，至少会保留一个
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode current = pHead;

        while (current != null){
            // 当前节点的下一个节点
            ListNode next = current.next;
            // 遍历相同节点并赋值，直到下一个节点是空或不同
            while (next != null && current.val == next.val){
                next = next.next;
            }
            current.next = next;
            // 如果下一个节点不同了就继续遍历
            current = current.next;
        }
        return pHead;
    }
    // 如果重复，一个不剩
    // 递推公式：
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        return rmDuplication(pHead);
    }

    // 当前节点与下一个节点不同，返回当前节点；当前节点与下n个系节点相等，返回下n+1个节点
    //
    private ListNode rmDuplication(ListNode next){
        // 空的或者只存在1个节点就不会有重复节点了
        if (next == null || next.next == null){
            return next;
        }

        ListNode sNext = next.next;
        // 当前节点和下一个节点不相等那么，下一个节点开始删除重复
        if(next.val != sNext.val){
            next.next = rmDuplication(next.next);
            return next;
        }

        // 当前节点和下n个节点相等, 找到下一个不同节点的指针
        while (sNext != null && next.val == sNext.val){
            sNext = sNext.next;
        }
        // 如果不是最后1次重复，那么返回此段重复节点的下一个节点
        // 如果是最后一个重复，那么返回null
        return rmDuplication(sNext);
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        RemoveDuplication removeDuplication = new RemoveDuplication();
        ListNode head = removeDuplication.deleteDuplication2(node1);

        System.out.println(head.val);
        System.out.println(head.next.val);
    }
}
