package leetcode.dp.bag;

/**
 * https://www.lintcode.com/problem/273/description?_from=problem_tag&fromId=undefined
 */
public class ExamStrategy_LintCode273 {
    public static void main(String[] args) {
        int[] p = {20, 50, 100, 5};
        int[] part = {20, 30, 60, 3};
        int[] f = {100, 80, 110, 10};
        int[] full = {60, 55, 88, 6};

        System.out.println(new Solution().exam(p, part, f, full));
    }

    /**
     * * 不做第i题: `dp[i][j] = dp[i-1][j]`
     * * 做第i题的部分: `dp[i][j] = dp[i-1][j-p[i-1]]+part[i-1]`
     * * 做第i题的所有: `dp[i][j] = dp[i-1][j-f[i-1]]+full[i-1]`
     * <p>
     * 三者取最大
     */
    static class Solution {
        /**
         * @param p:    The time you choose to do part of the problem.
         * @param part: The points you choose to do part of the problem.
         * @param f:    The time you choose to do the whole problem.
         * @param full: The points you choose to do the whole problem.
         * @return: Return the maximum number of points you can get.
         */
        public int exam(int[] p, int[] part, int[] f, int[] full) {
            // write your code here
            int n = p.length;

            int[][] dp = new int[n + 1][121];
            dp[0][0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= 120; j++) {
                    int v1 = 0;
                    int v2 = 0;
                    int v3 = 0;


                    // shik
                    v1 = dp[i - 1][j];

                    // part
                    if (j - p[i - 1] >= 0) {
                        v2 = dp[i - 1][j - p[i - 1]] + part[i - 1];
                    }

                    // full
                    if (j - f[i - 1] >= 0) {
                        v3 = dp[i - 1][j - f[i - 1]] + full[i - 1];
                    }

                    dp[i][j] = Math.max(v1, Math.max(v2, v3));
                }
            }

            int max = -1;

            for (int i = 0; i <= n; i++) {
                if (dp[i][120] > max) {
                    max = dp[i][120];
                }
            }

            return max;
        }
    }


}
