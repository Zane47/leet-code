package leetcode.dp.game;

import com.sun.corba.se.impl.resolver.FileResolverImpl;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * <p>
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * <p>
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，
 * 今晚能够偷窃到的最高金额。
 * <p>
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 */
public class HouseRobber2_213 {
    public static void main(String[] args) {

//        int[] nums = new int[]{2, 3, 2};
        int[] nums = new int[]{1, 2, 3, 1};

        System.out.println(new Solution().rob(nums));
    }


    /**
     * 成了一个环, 那么如果投了第n-1个, 就不能偷第0个
     * 如果没有偷第0个就可以偷第n-1个
     * <p>
     * 所以这里可以把理解成, 我偷两次, 只不过范围不一样, 然后投的策略和1是一样的
     * <p>
     * 例如: 6户人家, 两种偷法 , 取最大的那个
     * 0 1 2 3 4 5
     * 0 1 2 3 4
     * 1 2 3 4 5
     */
    static class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return nums[0];
            }
            if (n == 2) {
                return Math.max(nums[0], nums[1]);
            }

            int[] path1 = new int[n - 1];
            System.arraycopy(nums, 0, path1, 0, n - 1);

            int[] path2 = new int[n - 1];
            System.arraycopy(nums, 1, path1, 0, n - 1);

            return Math.max(rob1(path1), rob1(path2));
        }

        private int rob1(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(
                        dp[i - 1],
                        dp[i - 2] + nums[i]);
            }

            return dp[n - 1];
        }
    }
}