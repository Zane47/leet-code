package LeetCode;

// 拓展： lc918

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
        System.out.println(
                new Solution().maxSubArray(
                        new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }




    // 分治


    // dp


    // 贪心


    // 暴力
    static class Solution {
        public int maxSubArray(int[] nums) {
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                if () {

                }
            }

            return result;
        }
    }










}
