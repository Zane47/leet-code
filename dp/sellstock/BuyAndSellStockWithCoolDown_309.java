package leetcode.dp.sellstock;

/**
 * 你可以尽可能地完成更多的交易
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * # 状态方程
 * dp[i][k][1 or 0]
 * <p>
 * k = +inf
 * <p>
 * dp[i][0]
 * dp[i][1]
 * <p>
 * 影响的就是当要buy的时候, i-2
 * <p>
 * dp[i][0]不受影响, 因为是sell
 * dp[i][1]受影响, 因为是buy
 * <p>
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * <p>
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
 * <p>
 * <p>
 * # base case:
 * 初始化的时候也要修改
 * dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[0])
 * = max(0, -inf+prices[0])
 * = 0
 * dp[0][1] = max(dp[i-1][1], dp[i-2][0] - prices[0])
 * = max(dp[-1][1], dp[-2][0] - prices[0])
 * = max(-inf, -prices[0])
 * = -prices[0]
 * ---
 * dp[1][0] = max(dp[i-1][0], dp[i-1][1] + prices[1])
 * = max(dp[0][0], dp[0][1] + prices[1])
 * = max(0, -prices[0] + prices[1])
 * <p>
 * dp[1][1] = max(dp[i-1][1], dp[i-2][0] - prices[1])
 * = max(dp[0][1], dp[-1][0] - prices[1])
 * = max(-prices[0], -prices[1])
 */
public class BuyAndSellStockWithCoolDown_309 {
    public static void main(String[] args) {

        int[] prices = new int[]{1, 2, 3, 0, 2};

        System.out.println(new Solution().maxProfit(prices));
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length == 1) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            dp[1][0] = Math.max(0, -prices[0] + prices[1]);
            dp[1][1] = Math.max(-prices[0], -prices[1]);


            for (int i = 2; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }

            return dp[prices.length - 1][0];
        }
    }

}
