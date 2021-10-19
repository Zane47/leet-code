package leetcode.dp;

import java.util.Arrays;

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
     */
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int result = Integer.MAX_VALUE;
            int[][] memo = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], );
            }
            // 可能落在最后一行的任意一列, 做穷举, 看落到哪一列是最小的
            for (int j = 0; j < n; j++) {
                // [0, n-1]行
                // result = Math.min(result, dpBruteForce(matrix, n - 1, j));
                result = Math.min(result, dpWithMemo(matrix, n - 1, j));
            }

            return result;
        }

        private int dpWithMemo(int[][] matrix, int i, int j) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length) {
                return Integer.MAX_VALUE - 1;
            }
            if (i == 0) {
                return matrix[i][j];
            }

            if () {

            }

            return;
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
