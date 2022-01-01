package leetcode.dp.subsequence;

/**
 * 给定一个由整数数组 A表示的环形数组 C，求 C的非空子数组的最大可能和。
 * <p>
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。
 * <p>
 * (形式上，当0 <= i < A.length时C[i] = A[i], 且当i >= 0时C[i+A.length] = C[i])
 * <p>
 * 此外，子数组最多只能包含固定缓冲区 A中的每个元素一次。
 * (形式上，对于子数组C[i], C[i+1], ..., C[j],
 * 不存在i <= k1, k2 <= j 其中k1 % A.length = k2 % A.length)
 * <p>
 * <p>
 * 示例 1：
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * <p>
 * 示例 2：
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * <p>
 * 示例 3：
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * <p>
 * <p>
 * 示例 4：
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * <p>
 * 示例 5：
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 */
public class MaximumSumCircularSubarray_918 {
    public static void main(String[] args) {
//         int[] nums = new int[]{3, -1, 2, -1};
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // 全是负数
        // int[] nums = new int[]{-3, -2, -3};

        // 只有一个元素
        // int[] nums = new int[]{-2};

//        int[] nums = new int[]{8, -15, -29, -19};

        // 环形测试
        int[] nums = new int[]{5, -3, 5};
        System.out.println(new Solution().maxSubarraySumCircular(nums));
    }

    /**
     * 这道题目是53增加了环形的条件, 也就是说首尾相连, 那么最大的连续子序列有两种情况
     * 1. 如果不是首尾相连的, 那么就和53一样了, 直接求最大的连续子序列
     * 2. 如果是首尾相连的, 那么就反过来求序列和-最小的连续子序列, 而最小的连续子序列一定不会有首尾相连的情况
     * 总结来说, 就是求max(maxArraySum, sum-minArraysum)
     * <p>
     * <p>
     * 证明:
     * 为什么首尾相连的qingkuangxia,
     */
    static class Solution {
        public int maxSubarraySumCircular(int[] nums) {

            if (nums.length == 1) {
                return nums[0];
            }

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 最大子序列中是首尾不相连情况, 求连续子序列最大和
            int max1 = maxSubArray(nums);

            // 如果最大和max小于0；证明数组中没有正数；所以也就不用关心是不是环形的数组了 直接返回max
            // （一位都是负数难道你还绕过来加一个负数？）
            if (max1 < 0) {
                return max1;
            }

            // 大子序列中是首尾相连情况, 求连续最小序列最小和
            int min = minSubArray(nums);


            return Math.max(max1, sum - min);
        }

        /**
         * 最小的子序列和
         */
        private int minSubArray(int[] nums) {
            int pre = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < nums.length; i++) {
                // 注意这里是0, 也就是啥都不选
                // 如果全部都是正数, 因为题目整体要求的是最大值,
                pre = Math.min(nums[i] + pre, 0);

                // 这个也可以
                // pre = Math.min(nums[i] + pre, nums[i]);

                min = Math.min(min, pre);
            }
            return min;
        }

        /**
         * 这里就是lc_53的代码了
         */
        private int maxSubArray(int[] nums) {
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
}
