package leetcode.dp.subsequence;

/**
 * 给你一个整数数组 nums，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 * <p>
 * <p>
 * 示例1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * <p>
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * <p>
 * <p>
 * 示例 3：
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 * <p>
 * <p>
 * 示例 4：
 * 输入：nums = [-1,2]
 * 输出：1
 * <p>
 * <p>
 * 示例 5：
 * 输入：nums = [1,2,3,5,-6,4,0,10]
 * 输出：4
 *
 * // ------------------------ idea ------------------------
 * 乘积是正数的最长子序列
 * 这里和152相似的点在于, 都是乘积, 可以负负得正
 *
 * 所以dp[i], [0,i]的数组乘积正数的最大长度
 * 如果nums[i]是正数, 那么dpPos就要是正数最长的
 * 如果nums[i]是负数, 那么dpNeg就要是负数最长的
 * 所以要两个dp数组, dpPos和dpNeg, 分别代表的是结果是正数/负数的最长子序列
 * 然后根据nums[i]的正负情况进行添加.
 *
 *
 * 最后结果就是在dpPos中找max
 */
public class MaxLengthOfSubarrayWithPositiveProduct_1567 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, -2, -3, 4};
        System.out.println(new Solution().getMaxLen(nums));
    }


    static class Solution {
        public int getMaxLen(int[] nums) {


            return 0;
        }
    }
}
