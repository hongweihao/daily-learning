package mkii.datastru;

import java.util.Iterator;

/**
 * 顺序表实现
 *
 */
public class SeqList implements Iterator {

    private final static int DEFAULT_SIZE = 2;
    private final static int FACTORY = 2;

    private static int length;
    private static int size;
    private static int[] elems;

    SeqList() {
        initSeqList();
    }

    public int size(){
        return size;
    }

    public int length(){
        return length;
    }


    public void initSeqList(){
        length = 0;
        size = DEFAULT_SIZE;
        elems = new int[DEFAULT_SIZE];
    }

    public boolean emptySeqList(){
        return (elems == null || length == 0 || size == 0);
    }

    private void expansion(int[] a){

        int[] newElems = new int[size + FACTORY];
        int i;
        for(i = 0; i < length; i++){
            newElems[i] = a[i];
        }
        elems = newElems;
        size += FACTORY;
    }

    public void addElem(int val){

        if (length >= size){
            System.out.println("容量不足,扩容！");
            expansion(elems);
        }

        elems[length] = val;
        length++;
        System.out.println("添加元素成功");
    }

    public void deleteElem(int val){

        int i = 0;
        int index = -1;

        if (emptySeqList()){
            System.out.println("空顺序表！");
            return ;
        }

        for (i = 0; i < length; i++){
            if(elems[i] == val){
                index = i;
                elems[i] = 0;
            }
        }

        if (index == -1){
            System.out.println("未找到元素！");
        }

        for (i = index; i < length-1; i++){
            elems[i] = elems[i+1];
        }
        length--;
        System.out.println("删除元素成功");
    }

    public void updateSeqList(int index, int val){

        if(index >= size || index >= length || index < 0){
            System.out.println("索引错误！");
            return ;
        }

        elems[index] = val;
        System.out.println("更新元素成功");
    }

    public int readElem(int index) throws Exception{

        if(index >= size || index >= length || index < 0){
            System.out.println("索引错误！");
            throw new Exception("索引越界！");
        }
        System.out.println("查找元素成功");
        return elems[index];
    }

    public void destroySeqList(){

        if (!emptySeqList()){
            elems = null;
            length = 0;
            size = 0;
        }
    }


    @Override
    public boolean hasNext() {



        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
