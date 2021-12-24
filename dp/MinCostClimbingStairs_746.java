package leetcode.dp;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class MinCostClimbingStairs_746 {
    public static void main(String[] args) {
//        int[] cost = new int[]{10, 15, 20};
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        System.out.println(new Solution().minCostClimbingStairs(cost));
    }

    /**
     * dp[i]: 爬到第i个台阶的最小花费
     * dp[i]有两种可能:
     * 1. 第i-1号台阶, 支付了cost[i-1]然后爬1步上来的
     * 2. 第i-2号台阶, 支付了cost[i-2]然后爬2步上来的
     * 看哪个花费最小.Math.min()
     * <p>
     * n=3, 那么就要爬到第3号台阶, 0,1,2,3
     */
    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;

            if (n == 0) {
                return 0;
            }

            // 可以从0号台阶, 或者1号台阶开始爬, 那么就不需要任何花费
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;

            for (int i = 2; i <= n; i++) {
                dp[i] = Math.min(
                        dp[i - 1] + cost[i - 1],
                        dp[i - 2] + cost[i - 2]);
            }

            return dp[n];
        }
    }
}
