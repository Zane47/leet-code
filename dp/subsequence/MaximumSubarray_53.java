package leetcode.dp.subsequence;

import java.util.Arrays;

/**
 * 53.最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumSubarray_53 {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(new Solution().maxSubArray(nums));
    }

    /**
     * dp[i]：表示以 nums[i] 结尾 的 连续 子数组的最大和。
     * 状态转移：nums[i]是一定会被选中的，现在的问题就在于dp[i]是dp[i-1]+nums[i]，还是nums[i]
     * 因为我们可以看到，-3的情况下，dp[1] = -2+1 = -1, dp[2]可以是-2+1+(-3)，也可以直接是-3,
     * 就取决于dp[2] > 0？，是否有增益的效果
     *
     *
     * https://leetcode-cn.com/problems/maximum-subarray/solution/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
     */
    static class Solution {
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (dp[i-1] <= 0) {
                    dp[i] = nums[i];
                } else {
                    dp[i] = dp[i-1] + nums[i];
                }
                if (dp[i] > max) {
                    max = dp[i];
                }
            }

            return max;
        }
    }


    /**
     * dp
     */
    static class Solution1 {
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            // 取决于是否要加前一个数
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                // 这个数加上去()， 或者不加上去(从这个数开始)
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                if (max < dp[i]) {
                    max = dp[i];
                }
            }
            return max;
        }
    }


}
