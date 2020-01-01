package mkii.finger1;

/**
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 测试用例：
 * 1.正常数组，目标在数组中
 * 2.正常数组，目标不在数组中
 * 3.空数组
 *
 */
public class IntInBinaryArray {
    public boolean Find(int target, int [][] array) {

        if (array == null || array.length <= 0 || array[0].length <= 0){
            return false;
        }

        int row = 0;
        int col = array.length - 1;

        // 右上角的元素
        int topRight;

        while (row < array.length && col >= 0) {
            topRight = array[row][col];

            // 找到目标元素
            if (topRight == target) {
                return true;
            }

            if (topRight < target) {
                // 右上角元素小于目标元素，排除这一行
                row++;
            }else{
                // 右上角元素大于目标元素，排除这一列
                col--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        IntInBinaryArray intInBinaryArray = new IntInBinaryArray();
        /*int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };*/

        int[][] array = {{}};

        System.out.println(intInBinaryArray.Find(10, array));

    }
}
