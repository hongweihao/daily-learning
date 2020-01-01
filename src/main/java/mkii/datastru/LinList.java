package mkii.datastru;

import java.util.LinkedList;

/**
 * 链表实现
 *
 */
public class LinList {

    // first node
    private Node nodes;

    private static class Node {
        String data;
        Node next;

        Node() {
            data = null;
            next = null;
        }

        Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void initLinkedList(){
        nodes = new Node();
    }

    private Node getLastNode(){

        Node p = nodes;

        while (p.next != null){
            p = p.next;
        }
        return p;
    }

    private Node getNode(String data){

        if (data == null){
            System.out.println("数据为null！");
            return null;
        }

        Node p = nodes;
        while (p.next != null){
            if(data.equals(p.data)){
                return p;
            }
            p = p.next;
        }
        System.out.println("未找到符合条件的结点！");
        return null;
    }

    private Node getPreNode(String data){

        if (data == null){
            System.out.println("数据为null！");
            return null;
        }

        Node p = nodes;

        while (p.next != null ){

            if(data.equals(p.next.data)){
                return p;
            }
            p = p.next;
        }
        System.out.println("未找到符合条件的结点！");
        return null;
    }

    public void addNode(String data){

        Node node = new Node(data, null);
        Node lastNode = getLastNode();
        lastNode.next = node;

        System.out.println("添加结点！");
    }

    public void deleteNode(String data){

        if (data == null){
            System.out.println("数据为null！");
            return ;
        }

        Node p = nodes;

        while (p.next != null ){

            if(data.equals(p.next.data)){

                p.next = p.next.next;
                p.next.next = null;
                System.out.println("删除成功！");
                return;
            }
            p = p.next;
        }
        System.out.println("未找到符合条件的结点！");
    }

    public String readLinkedList(){

        StringBuilder builder = new StringBuilder();
        String spliteChar = ", ";

        for (Node p = nodes.next; p != null; p = p.next){

            builder.append(p.data);
            builder.append(spliteChar);

            if (p.next == null){
                break;
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) throws Exception{

        LinList linList = new LinList();

        linList.initLinkedList();

        linList.addNode("aa");
        linList.addNode("bb");
        linList.addNode("cc");

        System.out.println(linList.readLinkedList());

        linList.deleteNode("bb");

        System.out.println(linList.readLinkedList());

    }

}
