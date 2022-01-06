package leetcode.dp.sellstock;

/**
 *
 */
public class BestTimeToBuyAndSellStock_121_2 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new Solution().maxProfit(prices));

    }

    /**
     * solution2中可以看到状态只与前一天有关, 所以不需要数组, 只需要保存前一天数据即可
     *
     * # 三维
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
     *             = max(dp[i-1][1][1], -prices[i]);
     *
     * # 二维
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i]);
     * dp[i][1] = max(dp[i-1][1], -prices[i]);
     *
     * dp[0][0] = 0
     * dp[0][1] = -prices[i];
     *
     * # 只与前一个状态有关
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp_pre_0 = 0;
            int dp_pre_1 = -prices[0];

            int dp_cur_0 = 0;
            int dp_cur_1 = 0;

            for (int i = 1; i < n; i++) {
                dp_cur_0 = Math.max(dp_pre_0, dp_pre_1 + prices[i]);
                dp_cur_1 = Math.max(dp_pre_1, -prices[i]);

                dp_pre_0 = dp_cur_0;
                dp_pre_1 = dp_cur_1;
            }

            return dp_cur_0;
        }
    }




    /**
     * 按照笔记中的通用方法
     * <p>
     * dp[i][k][0 or 1]: 第i天, 最多交易次数为k,0: 没有持有股票, 1: 持有股票.
     * 最多获得多少利润。
     * <p>
     * 这里的因为只bug sell一次, 所以k = 1
     * 务必在buy的时候, k--;
     * <p>
     * # 状态转移:
     * 今天未持有股票:
     * dp[i][1][0]: max(dp[i-1][1][0], dp[i-1][1][1] + price[i])
     * 1. 昨天未持有股票, 今天啥都没干. rest
     * 2. 昨天持有股票, 今天卖了股票. sell
     * <p>
     * 今天持有股票:
     * dp[i][1][1]: max(dp[i-1][1][1], dp[i-1][0][0] - price[i])
     * 因为dp[i-1][0][0]: 最大交易次数为0, 并且不持有股票, 所以 = 0
     * dp[i][1][1] = max(dp[i-1][1][1], -price[i])
     * <p>
     * 1. 昨天也持有股票, 今天rest
     * 2. 昨天未持有股票, 今天buy
     * <p>
     * <p>
     * dp[i][1][0]: max(dp[i-1][1][0], dp[i-1][1][0] + price[i])
     * dp[i][1][1] = max(dp[i-1][1][1], -price[i])
     * 因为这里的k的次数固定是1, 所以这一维的状态可以不用管. 3维 -> 2维
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + price[i])
     * dp[i][1] = max(dp[i-1][1], -price[i])
     * <p>
     * <p>
     * # base case:
     * i-1, 所以i=0的情况
     * dp[0][0] = max(dp[-1][0], dp[-1][1] + price[0])
     * = max(0, -inf) = 0
     * 因为dp[-1][1], 在-1天的时候不可能持有股票
     * <p>
     * <p>
     * dp[0][1] = max(dp[-1][1], -price[0])
     * = -price[0]
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }

            return dp[n - 1][0];
        }
    }


    /**
     * 暴力, 超时
     * 找出数组中两个数字之间的最大差值
     * 双for
     */
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int max = 0;

            for (int i = 0; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int profit = prices[j] - prices[i];
                    if (profit > max) {
                        max = profit;
                    }
                }
            }
            return max;
        }
    }

}
