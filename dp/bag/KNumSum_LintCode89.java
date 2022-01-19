package leetcode.dp.bag;

/**
 * https://www.lintcode.com/problem/89/description?_from=problem_tag&fromId=undefined
 * 给定 n 个不同的正整数，整数 k(k \leq n)(k≤n)以及一个目标数字 target。
 * 在这 n 个数里面找出 k 个数，使得这 k 个数的和等于目标数字，求问有多少种方案？
 */
public class KNumSum_LintCode89 {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4};
        int k = 2;
        int target = 5;
        System.out.println(new Solution().kSum(A, k, target));
    }

    static class Solution {
        /**
         * @param A:      An integer array
         * @param k:      A positive integer (k <= length(A))
         * @param target: An integer
         * @return: An integer
         */
        public int kSum(int[] A, int k, int target) {
            // write your code here
            int n = A.length;
            int[][][] dp = new int[n + 1][k + 1][target + 1];

            dp[0][0][0] = 1;
            for (int i = 0; i < n; i++) {
                dp[i][0][0] = 1;
            }


            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    for (int t = 0; t <= target; t++) {
                        dp[i][j][t] = dp[i - 1][j][t];

                        if (t - A[i - 1] >= 0) {
                            dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                        }

                    }
                }
            }
            return dp[n][k][target];
        }
    }
}
