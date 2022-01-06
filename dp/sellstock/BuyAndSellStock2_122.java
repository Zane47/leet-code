package leetcode.dp.sellstock;

/**
 * k=inf
 * 无限次操作
 */
public class BuyAndSellStock2_122 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new Solution().maxProfit(prices));
    }


    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;

            // base case:
            int dp_pre_0 = 0;
            int dp_pre_1 = -prices[0];

            int dp_cur_0 = 0;
            int dp_cur_1 = 0;

            for (int i = 1; i < n; i++) {
                dp_cur_0 = Math.max(dp_pre_0, dp_pre_1 + prices[i]);
                dp_cur_1 = Math.max(dp_pre_1, dp_pre_0 - prices[i]);

                dp_pre_0 = dp_cur_0;
                dp_pre_1 = dp_cur_1;
            }

            return dp_cur_0;
        }
    }


    /**
     * 你可以尽可能地完成更多的交易(多次买卖一支股票), 所以k是+inf, 所以k和k-1就一样了
     * <p>
     * 状态转移方程:
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     * = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i]);
     * <p>
     * 也就是说, 和k无关了, 所以取消k的维度
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i]);
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]);
     * <p>
     * <p>
     * base case:
     * dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[0]) = 0
     * <p>
     * dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[0]) = -prices[0]
     */
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int n = prices.length;

            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }

            return dp[n - 1][0];
        }
    }

}
