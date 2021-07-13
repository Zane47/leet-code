package leetcode;
// 121. 买卖股票的最佳时机

public class MaxProfit_121 {

    public static void main(String[] args) {
        // Solution1 solution = new Solution1();
        Solution2 solution = new Solution2();
        // Solution solution = new Solution();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(solution.maxProfit(prices));

    }

    static class Solution {
        public int maxProfit(int[] prices) {

            return 0;
        }
    }

    // 一次遍历
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int profit = 0;
            for (int price : prices) {
                minPrice = Math.min(minPrice, price);
                profit = Math.max(profit, price - minPrice);
            }

            return profit;
        }
    }












    // 暴力法会超时
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int max = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int temp = prices[j] - prices[i];
                    if (max < temp) {
                        max = temp;
                    }
                }
            }
            return max;
        }
    }


}
