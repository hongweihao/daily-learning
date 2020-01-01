package mkii.datastru;

/**
 * 双向链表
 *
 * 实现增加和删除操作
 * 增加：如果链表是空的，就是添加到头结点
 *      否则添加到最后一个节点的下一个
 *
 * 删除：如果删除的节点是头结点/尾节点/中间节点
 *      头结点分为两种情况（只有头结点，存在后继节点）
 *
 */
public class DoubleLinkedList {
    private Node header; // 可以写一个方法获取这里的值

    // the structure of double linked list
    static class Node{
        String data;
        Node pre;
        Node next;

        Node(String data){
            this.data = data;
            pre = null;
            next = null;
        }
    }

    // initial the double linked list
    public DoubleLinkedList(){
        this.header = null;
    }

    // add a element to double linked list
    public boolean add(String data){
        if (data == null || data.length() <= 0) {
            return false;
        }
        Node lastNode = header;

        if (lastNode == null){
           header = new Node(data);
            System.out.println("the first node(" + data + ") has already been added!");
           return true;
        }

        // to find the last node
        while (lastNode.next != null){
            lastNode = lastNode.next;
        }
        // new node
        Node node = new Node(data);
        // link
        lastNode.next = node;
        node.pre = lastNode;

        System.out.println(data + " has already been added!");
        return true;
    }

    // delete a element from double liked list
    public boolean remove(String data){
        if (data == null || data.length() <= 0) {
            return false;
        }

        Node targetNode = header;

        while (targetNode != null && !targetNode.data.equals(data)){
            targetNode = targetNode.next;
        }

        // not found the node for data
        if (targetNode == null){
            System.out.println("Not found the node for " + data);
            return false;
        }

        // found the node
        Node pre = targetNode.pre;
        Node next = targetNode.next;

        // there is only header node
        if (pre == null && next == null){
            header = null;
        // remove the header node
        }else if (pre == null){
            header = next;
        // remove the last node
        }else if (next == null){
            pre = null;
        // remove the middle node
        }else {
            pre.next = next;
            next.pre = pre;
        }
        System.out.println(data + " has already been removed!");
        return true;
    }

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add("123");
        doubleLinkedList.add("456");*/
        //doubleLinkedList.add("acd");
        //doubleLinkedList.add("789");
        doubleLinkedList.remove("acdd");

        /*DoubleLinkedList.Node header = doubleLinkedList.header;
        while (header != null){
            System.out.println(header.data);
            header = header.next;
        }*/
    }
}
