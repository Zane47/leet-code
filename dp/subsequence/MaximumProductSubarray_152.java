package leetcode.dp.subsequence;

/**
 * 给你一个整数数组 nums，
 * 请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组
 * <p>
 * // ------------------------ idea ------------------------
 * 这里和53的序列和不一样, 因为是乘积, 所以会有负负得正的情况
 * 所以考虑下标为i的数字,
 * 1. 如果他是负数, 那么我们就希望他前一位的dp[i-1]也是负数, 并且负得越多越好
 * 2. 如果他是正数, 那么我们就希望他前一位的dp[i-1]是正数, 越大越好
 * <p>
 * 综上可以看到, 我们要维护两个dp数组:dpMax和dpMin
 * <p>
 * dpMax: 以第i个元素结尾的乘积最大子数组的乘积
 * dpMin: 以第i个元素结尾的乘积最小子数组的乘积
 * <p>
 * <p>
 * 状态转移:
 * dpMax[i]: 直接用Math.max比较最大的
 * 自己本身nums[i]
 * dpMin[i-1]*nums[i]
 * dpMax[i-1]*nums[i]
 * <p>
 * <p>
 * <p>
 * dpMin[i]: 比较最小的
 * 自己本身nums[i]
 * dpMin[i-1]*nums[i]
 * dpMax[i-1]*nums[i]
 * <p>
 * <p>
 * 结果就是dpMax[i]中的最大值
 */
public class MaximumProductSubarray_152 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};

        System.out.println(new Solution().maxProduct(nums));
    }

    static class Solution {
        public int maxProduct(int[] nums) {
            int[] dpMax = new int[nums.length];
            int[] dpMin = new int[nums.length];

            dpMax[0] = nums[0];
            dpMin[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                dpMax[i] = Math.max(
                        dpMax[i - 1] * nums[i],
                        Math.max(dpMin[i - 1]* nums[i], nums[i]));

                dpMin[i] = Math.min(dpMax[i - 1] * nums[i],
                        Math.min(dpMin[i - 1]* nums[i], nums[i]));
            }


            int max = dpMax[0];
            for (int num : dpMax) {
                if (num > max) {
                    max = num;
                }
            }

            return max;
        }
    }

}
