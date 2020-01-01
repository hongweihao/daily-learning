package mkii.sort;

public class Sort {

    public static void main(String[] args){

        int[] a = {3, 4, 1, 6, 2};

        Sort sort = new Sort();
        int[] b = sort.quickly(a, 0, 4);

        System.out.println(b.length);
    }

    /**
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    public int[] quickly(int[] a, int low, int high){

        int p = low;
        int q = high;
        int key = a[p];
        int temp;

        while (p < q){
            // 右边开始一直寻找
            while (a[q] > key) q--;
            // 找到了，与key交换位置
            if(a[q] < key){
                temp = a[q];
                a[q] = a[p];
                a[p] = temp;
            }

            // 右边找到之后就一直找左边
            while (a[p] < key) p++;
            if(a[p] > key ){
                temp = a[p];
                a[p] = a[q];
                a[q] = temp;
            }
        }// 已经把小于key的放左边，大于key的放右边

        //递归
        if (p > low) quickly(a, 0, p -1);
        if (q < high) quickly(a, p + 1, 4);

        return a;
    }

    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
