package mkii.datastru;

/**
 * 顺序表实现
 *
 */
public class SeqList {
    // 默认数组的大小
    private final static int DEFAULT_SIZE = 2;
    // 每次扩容增加的大小
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

    /**
     * 判断SeqList是否为空
     *
     * @return
     */
    public boolean emptySeqList(){
        return (elems == null || length == 0 || size == 0);
    }

    /**
     * 扩容方法
     *
     * @param a 原数组
     */
    private void expansion(int[] a){
        int[] newElems = new int[size + FACTORY];
        int i;
        for(i = 0; i < length; i++){
            newElems[i] = a[i];
        }
        elems = newElems;
        size += FACTORY;
    }

    /**
     * 在SeqList的尾部添加元素
     *
     * @param val 被添加元素
     */
    public void addElem(int val){
        if (length >= size){
            System.out.println("容量不足,扩容！");
            expansion(elems);
        }

        elems[length] = val;
        length++;
        System.out.println("添加元素成功");
    }

    /**
     * 根据元素的值删除一个元素。
     * 相当于判断SeqList中是否存在val
     * 如果存在1个则删除，如果存在多个则删除第一个
     *
     * @param val 被删除元素的值
     */
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

    /**
     * 根据索引，更新该位置的元素值
     *
     * @param index 索引
     * @param val 新值
     */
    public void updateSeqList(int index, int val){

        if(index >= size || index >= length || index < 0){
            System.out.println("索引错误！");
            return ;
        }

        elems[index] = val;
        System.out.println("更新元素成功");
    }

    /**
     * 根据索引获取该位置的元素值
     *
     * @param index 索引
     * @return
     * @throws Exception
     */
    public int readElem(int index) throws Exception{

        if(index >= size || index >= length || index < 0){
            System.out.println("索引错误！");
            throw new Exception("索引越界！");
        }
        System.out.println("查找元素成功");
        return elems[index];
    }

    /**
     * 回收SeqList，其实 JVM 会做回收
     *
     */
    public void destroySeqList(){
        if (!emptySeqList()){
            elems = null;
            length = 0;
            size = 0;
        }
    }
}
