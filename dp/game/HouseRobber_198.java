package leetcode.dp.game;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * <p>
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
 * 一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber_198 {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 1};
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(new Solution().rob(nums));
    }

    /**
     * dp[i]: 偷到第i家的时候, 能偷到的最多的前
     * dp[0]: 第0家, 那么必偷
     * dp[1]: 就看偷不偷0, 如果偷0, 那么1就不偷, 如果不偷0, 就偷1
     * 所以要看dp[0]和nums[1]的大小
     * <p>
     * 所以dp[i]取决于dp[i-1], 偷不偷第i-1户人家,
     * 如果偷i-1th: 那就偷不了nums[i], 钱就是dp[i-1]
     * 如果不偷i-1th: 那就要偷nums[i], 钱就是dp[i-2] + nums[i]
     * 看哪个大
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

            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            return dp[n - 1];
        }
    }

}
