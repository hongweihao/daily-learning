package mkii.finger1;

import java.util.ArrayList;
/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 *
 *
 */
public class FlatArray {
    public int[] multiply(int[] A) {
        if (A == null || A.length <= 1){
            return A;
        }


        return new int[1];
    }
}
