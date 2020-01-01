package mkii.finger1;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * <p>
 * 测试：
 * 1.正常
 * 2. null || length=0
 * 3.只有1行
 * 4.只有1列
 * 5.只有一行一列
 */
public class PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> numbers = new ArrayList<>();
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return numbers;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = rows;
        int col = cols;
        int line = 0;
        // 不一定行列相同，要小于最小的
        while ((line * 2) < Math.min(rows, cols)) {
            System.out.println(line << 2);
            int i, j;
            // up
            for (i = line; i < col; i++) {
                numbers.add(matrix[line][i]);
            }
            // right
            for (--i, j = line + 1; j < row; j++) {
                numbers.add(matrix[j][i]);
            }
            // bottom 如果只有一行的情况下，要保证只打印一次
            for (--j, --i; i >= line && j > line; i--) {
                numbers.add(matrix[j][i]);
            }
            // left， 入职只有一列的情况下，要保证只打印一次
            for (++i, --j; j > line && i < col - 1; j--) {
                numbers.add(matrix[j][i]);
            }
            row--;
            col--;
            line++;
        }
        return numbers;
    }

    public static void main(String[] args) {
         //int[][] matrix = {{1}};
        //int[][] matrix = {{1,2},{3,4},{5,6},{7,8},{9,10}};
        int[][] matrix = {{1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}};
        PrintMatrix printMatrix = new PrintMatrix();
        ArrayList<Integer> result = printMatrix.printMatrix(matrix);
        System.out.println(result.size());
    }
}
