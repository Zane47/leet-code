package leetcode.dp.sellstock;

public class BuyAndSellStock4_188 {
    public static void main(String[] args) {

        int[] prices = new int[]{};
        int k = 2;

        System.out.println(new Solution().maxProfit(k, prices));
    }

    static class Solution {
        public int maxProfit(int k, int[] prices) {
            if (prices.length == 0) {
                return 0;
            }


            int[][][] dp = new int[prices.length][k + 1][2];

            // base case
            for (int v1 = 1; v1 <= k; v1++) {
                dp[0][v1][0] = 0;
                dp[0][v1][1] = -prices[0];
            }

            for (int i = 1; i < prices.length; i++) {
                for (int v1 = 1; v1 <= k; v1++) {
                    // base case: i==1
                    /*if (i == 0) {
                        // base case
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }*/

                    dp[i][v1][0] = Math.max(
                            dp[i - 1][v1][0],
                            dp[i - 1][v1][1] + prices[i]);

                    dp[i][v1][1] = Math.max(
                            dp[i - 1][v1][1],
                            dp[i - 1][v1 - 1][0] - prices[i]);
                }
            }

            return dp[prices.length - 1][k][0];
        }
    }

    static class SolutionRef {
        int maxProfit(int max_k, int[] prices) {
            int n = prices.length;
            if (n <= 0) {
                return 0;
            }
            if (max_k > n / 2) {
                // 交易次数 k 没有限制的情况
                // return maxProfit(prices);
            }

            // base case：
            // dp[-1][...][0] = dp[...][0][0] = 0
            // dp[-1][...][1] = dp[...][0][1] = -infinity
            int[][][] dp = new int[n][max_k + 1][2];
            // k = 0 时的 base case
            for (int i = 0; i < n; i++) {
                dp[i][0][1] = Integer.MIN_VALUE;
                dp[i][0][0] = 0;
            }

            for (int i = 0; i < n; i++)
                for (int k = max_k; k >= 1; k--) {
                    if (i - 1 == -1) {
                        // 处理 i = -1 时的 base case
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }
                    dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
                }
            return dp[n - 1][max_k][0];
        }


    }



}
