package leetcode.dp;

import java.util.Arrays;

/**
 * 给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix =
 * [[2,1,3],
 * [6,5,4],
 * [7,8,9]]
 * 输出：13
 * 解释：下面是两条和最小的下降路径，用加粗+斜体标注：1->5->7, 1 -> 4 -> 8
 * <p>
 * 示例 2：
 * 输入：matrix =
 * [[-19,57],
 * [-40,-5]]
 * 输出：-59
 * 解释：下面是一条和最小的下降路径，用加粗+斜体标注：
 * -19 -> -40
 * <p>
 * 示例 3：
 * 输入：matrix = [[-48]]
 * 输出：-48
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumFallingPathSum_931 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        System.out.print(new Solution().minFallingPathSum(matrix));
    }

    /**
     * (i, j) -> (i-1, j), (i-1, j-1), (i-1, j+1)
     * int dp(matrix, i, j): 从第一行matrix(0, ...),一直落到位置matrix[i][j]的最小路径和
     * <p>
     * 需要注意的是
     * 1.memo初始化的值, 需要知道memo是否存储了结果, 所以根据题目的范围来进行规划
     *  1 <= n <= 100, -100 <= matrix[i][j] <= 100
     *  memo要取取不到的值, max是 100 * 100 = 10000, min是-100*100 = -10000, 区间[-10000, 10000]
     *  所以memo初始化在这个区间之外就可以了
     *
     * 2.数据越界情况返回非法值
     *  应返回一个取不到的值,
     *
     */
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int result = Integer.MAX_VALUE;
            int[][] memo = new int[n][n];
            for (int i = 0; i < n; i++) {
                // 这边的初始化是多少?
                Arrays.fill(memo[i], 10001);
            }
            // 可能落在最后一行的任意一列, 做穷举, 看落到哪一列是最小的
            for (int j = 0; j < n; j++) {
                // [0, n-1]行
                // result = Math.min(result, dpBruteForce(matrix, n - 1, j));
                result = Math.min(result, dpWithMemo(matrix, n - 1, j, memo));
            }

            return result;
        }

        /**
         * 带备忘录的方法
         */
        private int dpWithMemo(int[][] matrix, int i, int j, int[][] memo) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length) {
                return 100002;
            }
            if (i == 0) {
                return matrix[i][j];
            }
            // 读取备忘录
            if (memo[i][j] != 10001) {
                return memo[i][j];
            }

            memo[i][j] =
                min(dpWithMemo(matrix, i - 1, j, memo),
                    dpWithMemo(matrix, i - 1, j - 1, memo),
                    dpWithMemo(matrix, i - 1, j + 1, memo));

            return memo[i][j];
        }

        /**
         * 暴力穷举
         * 对于matrix[i][j], 只能从matrix[i-1][j], matrix[i-1][j-1], matrix[i-1][j+1]这三个位置过来
         * <p>
         * 只要知道到达 (i-1, j), (i-1, j-1), (i-1, j+1) 这三个位置的最小路径和，
         * 加上 matrix[i][j] 的值，就能够计算出来到达位置 (i, j) 的最小路径和：
         */
        private int dpBruteForce(int[][] matrix, int i, int j) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length) {
                // 非法情况返回特殊值
                return Integer.MAX_VALUE - 1;
            }
            // base case, 到第一行
            if (i == 0) {
                return matrix[i][j];
            }

            // 状态转移, matrix[i][j] + min(三个位置)
            return matrix[i][j] +
                min(dpBruteForce(matrix, i - 1, j),
                    dpBruteForce(matrix, i - 1, j - 1), dpBruteForce(matrix, i - 1, j + 1));
        }


        private int min(int matrix, int matrix1, int matrix2) {
            return Math.min(matrix, Math.min(matrix1, matrix2));
        }
    }

}
