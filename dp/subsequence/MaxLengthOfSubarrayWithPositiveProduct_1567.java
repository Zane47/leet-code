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
 * <p>
 * // ------------------------ idea ------------------------
 * 乘积是正数的最长子序列
 * 这里和152相似的点在于, 都是乘积, 可以负负得正
 * <p>
 * 所以dp[i], [0,i]的数组乘积正数的最大长度
 * dpPos: 以下标i为结尾的乘积为正数的最长子数组长度
 * dpNeg: 以下标i为结尾的乘积为负数的最长子数组长度
 * <p>
 * 如果nums[i]是正数, 那么dpPos就要是正数最长的
 * 如果nums[i]是负数, 那么dpNeg就要是负数最长的
 * 所以要两个dp数组, dpPos和dpNeg, 分别代表的是结果是正数/负数的最长子序列长度
 * 然后根据nums[i]的正负情况进行添加.
 * <p>
 * 状态转移:
 * dpPos[i]:
 * if nums[i] == 0, dpPos[i] = 0;
 * if nums[i] > 0, dpPos[i] = dpPos[i-1] + 1;
 * if nums[i] < 0, dpPos[i] = dpNeg[i-1] + 1; 负数了, 那么就要是负负得正
 * <p>
 * dpNeg:
 * if nums[i] == 0, dpNeg[i] = 0;
 * if nums[i] < 0, dpNeg[i] = dpPos[i-1] + 1;(负数的时候, 前一个数字得是正数)
 * if nums[i] > 0, dpNeg[i] = dpNeg[i-1] + 1(正数的时候, 前一个数字是负数, 乘起来还是正数)
 * <p>
 * 最后结果就是在dpPos中找max
 * <p>
 * 初始化:
 * i与i-1状态有关, 所以就初始化i==0的情况
 * if nums[0] == 0, dpPos[0] = dpNeg[0] = 0;(以0结尾, 0*其他的都是0)
 * if nums[0] > 0, dpPos[0] = 1;
 * if nums[0] < 0, dpNeg[0] = 1;
 */
public class MaxLengthOfSubarrayWithPositiveProduct_1567 {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, -2, -3, 4};

//        int[] nums = new int[]{0, 1, -2, -3, -4};
        int[] nums = new int[]{-1, -2, -3, 0, 1};
//        int[] nums = new int[]{-1, 2};
//        int[] nums = new int[]{1, 2, 3, 5, -6, 4, 0, 10};

        System.out.println(new Solution().getMaxLen(nums));
    }


    static class Solution {
        public int getMaxLen(int[] nums) {
            int[] dpPos = new int[nums.length];
            int[] dpNeg = new int[nums.length];

            if (nums[0] == 0) {
                dpPos[0] = 0;
                dpNeg[0] = 0;
            } else if (nums[0] > 0) {
                dpPos[0] = 1;
            } else {
                dpNeg[0] = 1;
            }

            if (nums.length == 1) {
                return dpPos[0];
            }

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > 0) {
                    dpPos[i] = dpPos[i - 1] + 1;

                    // 前一个dpNeg是0, nums[i]是正数的时候, 以i为结尾的序列乘积还是0
                    if (dpNeg[i - 1] == 0) {
                        dpNeg[i] = 0;
                    } else {
                        dpNeg[i] = dpNeg[i - 1] + 1;
                    }
                } else if (nums[i] < 0) {
                    dpNeg[i] = dpPos[i - 1] + 1;

                    // 前一个dpNeg是0, nums[i]是负数的时候, 以i为结尾的序列乘积还是0
                    if (dpNeg[i - 1] == 0) {
                        dpPos[i] = 0;
                    } else {
                        // 前一个结尾的负数乘积, 并且nums[i]也是负数, 乘起来就是正数了
                        dpPos[i] = dpNeg[i - 1] + 1;
                    }
                } else {
                    dpPos[i] = 0;
                    dpNeg[i] = 0;
                }
            }

            int maxLen = 0;
            for (int len : dpPos) {
                if (len > maxLen) {
                    maxLen = len;
                }
            }


            return maxLen;
        }
    }
}
