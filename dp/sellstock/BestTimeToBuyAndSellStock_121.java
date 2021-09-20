package leetcode.dp.sellstock;
// 121. 买卖股票的最佳时机

public class BestTimeToBuyAndSellStock_121 {

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

    /**
     * 这道题使用贪心算法会比较简单，贪心算法是找出一个个局部最优解，
     * 通过这些局部最优解再得到全局最优解。
     * 那么我们看这道题，按照题意只能进行一次买卖。为了使利润最大化，要尽量以一个较低的价格买入，
     * 较高的价格抛出。将问题分解为局部问题，则是找到当天对应最大利润，最后得到最大值即可。
     *
     * 具体思路：
     * 1.首先定义最低价格和最大利润两个变量
     * 2.遍历整个数组
     * (1)将最低价格和当前价格比较，低的为新的最低价格；
     * (2)将当前价格减去最低价格的值与最大利润比较，高的为新的最大利润
     * 3.返回最大利润
     *
     * 作者：lan-ch
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-ta-5xfw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int profit = 0;
            for (int price : prices) {
                //将最低价格和当前价格比较，低的为新的最低价格
                minPrice = Math.min(minPrice, price);
                //将当前价格减去最低价格的值与最大利润比较，高的为新的最大利润
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
