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
     * 假如计划在第 i 天卖出股票，那么最大利润的差值一定是在[0, i-1] 之间选最低点买入
     * 所以遍历数组，依次求每个卖出时机的的最大差值，再从中取最大值
     */
    static class Solution {
        public int maxProfit(int[] prices) {


            return 0;
        }
    }


    /**
     * 暴力超时
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
