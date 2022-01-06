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
     *
     * # 状态转移:
     * k = 2;
     *
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     *
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     *
     * # base case:
     * dp[0][2][0] = max(dp[-1][2][0], dp[-1][2][1] + prices[0])
     *
     * dp[0][2][1] = max(dp[-1][2][1], dp[-1][1][0] - prices[0])
     *
     * 这里对k也要进行穷举, 遍历状态
     *
     *
     */
    static class Solution {
        public int maxProfit(int[] prices) {

            return 0;
        }
    }


}
