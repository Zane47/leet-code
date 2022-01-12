package leetcode.dp.sellstock;

/**
 * k = 2
 * 两笔交易
 */
public class BuyAndSellStock3_123 {
    public static void main(String[] args) {


    }

    /**
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * <p>
     * # 状态转移:
     * k = 2;
     * <p>
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * <p>
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * <p>
     * # base case:
     * dp[0][2][0] = max(dp[-1][2][0], dp[-1][2][1] + prices[0])
     * <p>
     * dp[0][2][1] = max(dp[-1][2][1], dp[-1][1][0] - prices[0])
     * <p>
     * 这里对k也要进行穷举, 遍历状态
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            int maxK = 2;

            int[][][] dp = new int[prices.length][maxK + 1][2];

            // base case: i==1
            dp[0][1][0] = 0;
            dp[0][1][1] = -prices[0];

            dp[0][2][0] = 0;
            dp[0][2][1] = -prices[0];

            for (int i = 1; i < prices.length; i++) {
                for (int k = 1; k <= maxK; k++) {
                    /*if (i == 0) {
                        // base case
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }*/

                    dp[i][k][0] = Math.max(
                            dp[i - 1][k][0],
                            dp[i - 1][k][1] + prices[i]);

                    dp[i][k][1] = Math.max(
                            dp[i - 1][k][1],
                            dp[i - 1][k - 1][0] - prices[i]);
                }
            }

            return dp[prices.length - 1][2][0];
        }
    }


}
