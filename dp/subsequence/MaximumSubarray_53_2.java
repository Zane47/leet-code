package leetcode.dp.subsequence;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * <p>
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * // ------------------------ solution ------------------------
 * dp: 以下标i结尾的连续子数组的最大和. -> 只需要求出每个位置的 dp[i]，然后返回最大值即可
 * dp[0] = nums[0]
 * dp[1] = Math.max(nums[1], dp[0] + nums[1])
 * 连续如何体现: nums[i] 单独成为一段还是加入f(i−1) 对应的那一段，
 * 这取决于 nums[i] 和 f(i−1)+nums[i] 的大小
 * <p>
 * 到了第i个位置, 根据nums[i]是否要加入来判断
 * 如果不加入, 那就是nums[i]自己重新开始. 之前的连续序列不要了
 * 如果加入, dp[i-1] + nums[i]
 * 看两个哪个更大 -> 状态转移方程
 * dp[i] = Math.max(dp[i-1], dp[i-1] + nums[i]) -> solution1
 * <p>
 * <p>
 * dp[i]只与前一个状态dp[i-1]有关, 所以只需要用一个数字来保存即可, 降低空间复杂度
 * 同时在-3的情况下，dp[1] = -2+1 = -1, dp[2]可以是-2+1+(-3) = -4，也可以直接是-3,
 * 就取决于dp[2] > 0？，是否有增益的效果 ->solution2
 */
public class MaximumSubarray_53_2 {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = new int[]{-2, -1};
        System.out.println(new Solution2().maxSubArray(nums));
    }


    /**
     * solution2
     */
    static class Solution2 {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            if (len == 1) {
                return nums[0];
            }

            // 前一个数字
            int pre = nums[0];
            int dp = nums[0];

            int max = nums[0];

            for (int i = 1; i < len; i++) {
                if (pre > 0) {
                    dp = nums[i] + pre;
                } else {
                    dp = nums[i];
                }
                // 更新pre,
                // 其实这里不需要dp, 直接都用pre就可以了
                pre = dp;

                if (dp > max) {
                    max = dp;
                }

            }
            return max;
        }
    }

    /**
     * solution1
     */
    static class Solution1 {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            if (len == 1) {
                return nums[0];
            }

            int[] dp = new int[len];
            dp[0] = nums[0];

            int max = nums[0];

            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
                if (dp[i] > max) {
                    max = dp[i];
                }
            }

            return max;
        }
    }
}
