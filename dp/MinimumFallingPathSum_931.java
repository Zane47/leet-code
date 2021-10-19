package leetcode.dp;

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
     *
     */
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int result = Integer.MAX_VALUE;
            // 可能落在最后一行的任意一列
            for (int j = 0; j < n; j++) {
                result = Math.min(result, dp(matrix, n - 1, j));
            }

            return result;
        }

        private int dp(int[][] matrix, int i, int j) {




            return 0;
        }
    }

}
