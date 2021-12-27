package leetcode.dp.game;

import java.util.Arrays;

/**
 * 这道题目就是打家劫舍
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个nums[i]，
 * 删除它并获得nums[i]的点数。之后，
 * 你必须删除所有等于nums[i] - 1 和 nums[i] + 1的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * <p>
 * <p>
 * 示例2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数
 */
public class DeleteAndEarn_740 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 2};
//        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        System.out.println(new Solution().deleteAndEarn(nums));
    }

    /**
     * ount=[0, 0, 2, 3, 1]. 0个0, 0个1, 2个2, 3个3, 1个4
     * <p>
     * 状态方程的思考过程:
     * <p>
     * dp[i]: 到了第i个位置上, result的最大值, (到第i个位置, 删除数字的和最大)
     * <p>
     * 第i个位置的数字可以删除也可以不删除, 依据是: 两种前结果之上进行选择的：
     * <p>
     * 1. 如果你不删除当前位置的数字，那么你得到就是前一个数字的位置的最优结果。
     * 2. 如果你觉得当前的位置数字i需要被删，那么你就会得到i - 2位置的那个最优结果加上当前位置的数字乘以个数。
     * <p>
     * dp[i] = max(dp[i-1], dp[i-2] + count[i] * i)
     */
    static class Solution {
        public int deleteAndEarn(int[] nums) {
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }

            int max = -1;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }

            // 数字出现的次数
            // count[m] = n, 数字m出现了n次
            int[] count = new int[max + 1];

            // count数组记录, nums数组中数字出现的个数
            for (int num : nums) {
                count[num]++;
            }

            // dp: 到数字i, result的最大值, 看数字i取不取, 取与不取有两种情况
            int[] dp = new int[max + 1];
            // 数字0, 没用
            dp[0] = 0;
            dp[1] = count[1] * 1;
            for (int i = 2; i <= max; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + count[i] * i);
            }

            return dp[max];
        }
    }

}
