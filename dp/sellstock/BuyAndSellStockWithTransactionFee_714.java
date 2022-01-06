package leetcode.dp.sellstock;

/**
 * 对股票买卖2的扩展
 * 无限次地完成交易, 但是你每笔交易都需要付手续费
 * k=inf, 交易加上fee
 */
public class BuyAndSellStockWithTransactionFee_714 {
    public static void main(String[] args) {

        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(new Solution().maxProfit(prices, fee));

    }


    /**
     * # 状态转移1
     * ## buy的时候交手续费
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
     * <p>
     * ## sell时缴费的base case
     * dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[0])
     * = max(0, -inf) = 0
     * <p>
     * dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[0] - fee)
     * = max(-inf, -price[0] - fee) = -price[0] - fee
     */
    static class Solution {
        public int maxProfit(int[] prices, int fee) {

            int[][] dp = new int[prices.length][2];

            dp[0][0] = 0;
            dp[0][1] = -prices[0] - fee;

            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            }


            return dp[prices.length - 1][0];
        }
    }

    /**
     * sell的时候交手续费
     * dp[i][k][0]
     * <p>
     * dp[i][k][1]
     * <p>
     * <p>
     * 因为有手续费, 所以在sell或者buy的时候减去手续费
     * k是inf, 不再考虑
     * <p>
     * # 状态转移1
     * ## sell的时候交手续费
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     * <p>
     * ## sell时缴费的base case
     * dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[0] - fee)
     * = max(0, -inf) = 0
     * <p>
     * dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[0])
     * = max(-inf, -price[0]) = -price[0]
     */
    static class Solution1 {
        public int maxProfit(int[] prices, int fee) {
            int[][] dp = new int[prices.length][2];

            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }


            return dp[prices.length - 1][0];
        }
    }

}
