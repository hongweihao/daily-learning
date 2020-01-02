package mkii.finger1;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * 测试：
 * list1=null || list2 = null
 *
 */
public class MergeList {
    public ListNode merge(ListNode list1,ListNode list2) {
        // 注意这里，可能只有一个链表为空的情况
        // 这里也可以保证，递归过程中，其中一个链表已经遍历完结点，另一个链表能接上
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val){
            head = list1;
            head.next = merge(list1.next, list2);
        }else{
            head = list2;
            head.next = merge(list1, list2.next);
        }
        return head;
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode head;
        if(list1.val > list2.val){
            head = list2;
            head.next = Merge(list1, list2.next);
        }else{
            head = list1;
            head.next = Merge(list1.next, list2);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode list11 = new ListNode(1);
        ListNode list12 = new ListNode(3);
        ListNode list13 = new ListNode(5);
        ListNode list14 = new ListNode(7);

        list11.next = list12;
        list12.next = list13;
        list13.next = list14;

        ListNode list21 = new ListNode(2);
        ListNode list22 = new ListNode(4);
        ListNode list23 = new ListNode(6);
        ListNode list24 = new ListNode(8);

        list21.next = list22;
        list22.next = list23;
        list23.next = list24;

        MergeList mergeList = new MergeList();
        ListNode listNode = mergeList.merge(list11, list21);

        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
        System.out.println(listNode.next.next.next.val);
    }
}
