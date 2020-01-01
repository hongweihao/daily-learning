package mkii.finger1;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * (这里问题的本质是能否到达？而不是一条路最多走多少格子，所以应该是4条路的结果都加起来，但又不能重复走。。)
 *
 * 测试：
 * 1. <=0
 */
public class Machine {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold <= 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        // 记录是否占用
        boolean[] visited = new boolean[rows * cols];
        return checkPath(threshold, rows, cols, 0, 0, visited);
    }

    /**
     * 边界检查和占用检查
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    private int checkPath(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
        int count = 0;
        // 占用检查和边界检查
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row * cols + col] && getDigit(row, col) <= threshold) {
            //count++;
            visited[row * cols + col] = true;
            //System.out.println(count + " ---> (" + row + ", " + col + ")");

            // 检查4个格子
            count = 1 + checkPath(threshold, rows, cols, row - 1, col, visited) +
                    checkPath(threshold, rows, cols, row + 1, col, visited) +
                    checkPath(threshold, rows, cols, row, col - 1, visited) +
                    checkPath(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    /**
     * 根据坐标计算数位和
     *
     * @param row
     * @param col
     * @return
     */
    private int getDigit(int row, int col) {
        int sum = 0;
        row = Math.abs(row);
        col = Math.abs(col);
        do {
            sum += row % 10;
        } while ((row /= 10) > 0);

        do {
            sum += col % 10;
        } while ((col /= 10) > 0);

        return sum;
    }

    public static void main(String[] args) {
        Machine machine = new Machine();
        System.out.println(machine.movingCount(5, 10, 10)); // 21
    }
}
