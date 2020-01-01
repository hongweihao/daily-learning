package mkii.datastru;

public class Test {


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

    public void seqListTest() throws Exception{

        SeqList seqList = new SeqList();

        System.out.println(seqList.emptySeqList());

        seqList.initSeqList();

        System.out.println(seqList.emptySeqList());

        seqList.addElem(23);
        seqList.addElem(24);
        seqList.addElem(25);

        System.out.println(seqList.emptySeqList());
        System.out.println(seqList.length());

        seqList.deleteElem(23);

        System.out.println(seqList.length());

        seqList.updateSeqList(1, 35);

        System.out.println(seqList.readElem(1));

    }




}
