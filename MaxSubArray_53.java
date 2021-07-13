package leetcode;

// 拓展： lc918: 环形子数组的最大和

/**
 * 最大和的连续子数组。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 *
 * 输入：nums = [1]
 * 输出：1
 *
 */
public class MaxSubArray_53 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(
                new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }




    // 分治


    // dp


    /** 贪心 https://leetcode-cn.com/problems/maximum-subarray/solution/53-zui-da-zi-xu-he-bao-li-tan-xin-xiang-jie-by-car/
     * 贪心的点在于：当碰到负数导致当前的sum都变成负数的时候，放弃
     * 例如-2 和 1在一起的时候，肯定是从1开始算起
     *
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，
     * 因为负数加上下一个元素 “连续和”只会越来越小。
     * 全局最优：选取最大“连续和”
     */
    static class Solution {
        public int maxSubArray(int[] nums) {
            int result = Integer.MIN_VALUE;
            int tempSum = 0;
            for (int i = 0; i < nums.length; i++) {
                tempSum += nums[i];
                if (tempSum > result) {
                    result = tempSum;
                }
                if (tempSum < 0) {
                    tempSum = 0;
                }
            }
            return result;
        }
    }



    // 暴力
    static class Solution1 {
        public int maxSubArray(int[] nums) {
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int tempSum = 0;
                for (int j = i; j < nums.length; j++) {
                    tempSum += nums[j];
                    if (tempSum > result) {
                        result = tempSum;
                    }
                }
            }

            return result;
        }
    }










}
