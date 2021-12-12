package leetcode.dp.bag;

import java.util.Arrays;

/**ac
 * 将数组划分为两组， 两组分别求和之后的差最小:
 * <p>
 * 给出一个正整数数组，写一个程序把这个整数数组分成S1跟S2两部分，
 * 使S1中的和跟S2中的和的差的绝对值最小。
 * 换句话讲，如果有一个一个整数数组 S 有 n 个数，如果Subset1有 m 个数，
 * Subset2必须有 n-m 个数并且 abs(sum(Subset1) – sum(Subset2)) 应该最小
 * <p>
 * <p>
 * -> x与sum-x之间的差距尽可能小
 * 题意转换为 -> s1中的和尽可能接近sum / 2 -> 数组中挑选若干个数, 和尽可能接近sum / 2
 */
public class MinimumPartition_LintCode724 {
    public static void main(String[] args) {
        // int[] nums = {1, 6, 11, 5};
        int[] nums = {1, 2, 3, 4};
        System.out.println(new Solution().findMin(nums));
    }

    static class Solution {
        /**
         * @param nums: the given array
         * @return: the minimum difference between their sums
         */
        public int findMin(int[] nums) {
            // write your code here
            int n = nums.length;
            int sum = Arrays.stream(nums).sum();
            int V = sum / 2;
            int[][] dp = new int[n + 1][V + 1];

            for (int i = 1; i <= n; i++) {
                for (int v = 1; v <= V; v++) {
                    if (v < nums[i - 1]) {
                        dp[i][v] = dp[i - 1][v];
                    } else {
                        dp[i][v] = Math.max(
                                dp[i - 1][v],
                                dp[i - 1][v - nums[i - 1]] + nums[i - 1]);
                    }
                }
            }

            // dp: 前i个数字, 能凑到的最大的元素和
            int halfMax = 0;
            for (int v = 0; v <= V; v++) {
                if (dp[n][v] > halfMax) {
                    halfMax = dp[n][v];
                }
            }

            return Math.abs((sum - halfMax) - halfMax);

        }
    }

}
