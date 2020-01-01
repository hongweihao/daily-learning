package mkii.finger1;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * 注意：
 * 1. 越界
 * 2. 递归设置出口
 *
 * 测试：
 * 1. {'b','c', 'c', 'e', 'a'} false
 * 2. {'b','c', 'c', 'e', 'd'} true
 * 3. null {}
 * ...
 * 1.matrix的测试用例
 */
public class Matrix {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {

        if(str == null || str.length <= 0 || matrix == null || matrix.length <= 0 || rows <= 0 || cols <= 0){
            return false;
        }

        // 记录是否走过
        int[] sign = new int[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 找到第一个字符
                if (nextChar(matrix, rows, cols, i, j, str, 0, sign)){
                    return true;
                }else{
                    // 擦除痕迹
                    sign = new int[rows * cols];
                }
            }
        }
        return false;
    }

    public boolean nextChar(char[] matrix, int rows, int cols, int i, int j, char[] str, int p, int[] sign){

        // 出口,str的字符全部可以找到位置
        if (str.length == p){
            return true;
        }
        // 越界
        if (i >= 0 && i < rows && j >= 0 && j < cols) {
            // 这个位置已经被走过了
            if (sign[i * cols + j] == 1) {
                return false;
            }
            // 不符合字符
            if (matrix[i * cols + j] != str[p]) {
                return false;
            }
            // 标记走过
            sign[i * cols + j] = 1;
            // 判断4个相邻位置是否能够走
            if (nextChar(matrix, rows, cols, i + 1, j, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i, j + 1, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i - 1, j, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i, j - 1, str, p + 1, sign)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String matrixStr =  "ABCEHJIG" +
                            "SFCSLOPQ" +
                            "ADEEMNOE" +
                            "ADIDEJFM" +
                            "VCEIFGGS";
        String strStr = "SGGFIECVAASABCEHJIGQEM";
        char[] matrix = matrixStr.toCharArray();
        char[] str = strStr.toCharArray();
        Matrix ma = new Matrix();
        System.out.println(ma.hasPath(matrix,5, 8, str));
    }

}
