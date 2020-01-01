package mkii.datastru;

/**
 * 链式栈
 * 结构：双向链表(弹出栈顶元素的时候快速指向前一个)，栈顶指针，栈底指针（头）
 *
 */
public class LinkedStack {
    private Node header;
    private Node top;

    static class Node{
        int data;
        Node pre;
        Node next;
        Node(int data){
            this.data = data;
            pre = null;
            next = null;
        }
    }

    public LinkedStack(){
        this.header = null;
        this.top = null;
    }

    public void push(int data){
        Node node = new Node(data);
        // 空栈种压入数据
        if (top == null && header == null){
            header = node;
            top = node;
        }else {
            top.next = node;
            node.pre = top;
            top = top.next;
        }
        System.out.println(data + " has been pushed!");
    }

    public int pop() throws Exception{
        if (top == null && header == null){
            throw new Exception("the stack is empty!");
        }
        // 栈顶元素
        Node node = top;
        // 栈中只有一个元素
        if (header == top){
            header = null;
            top = null;
            return node.data;
        }else {
            // 移到前一个节点
            top = node.pre;
            // 断开与最后一个节点的链接
            top.next = null;
            return node.data;
        }
    }

    public int top() throws Exception{
        if (top == null && header == null){
            throw new Exception("the stack is empty!");
        }
        // 栈顶元素
        Node node = top;
        return node.data;
    }

    public static void main(String[] args) throws Exception{
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);
        System.out.println(linkedStack.top());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
    }
}
