package mkii.finger1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * 测试：
 * 1. 空链表
 * 2. 正常链表
 */
public class ReversePrintLinkedList {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        if (listNode == null){
            return arrayList;
        }

        Stack<Integer> stack = new Stack<>();

        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }
}
