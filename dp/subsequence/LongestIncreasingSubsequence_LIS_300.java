package leetcode.dp.subsequence;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * <p>
 * 这里要注意的是: [子序列]和[子串]这两个名词的区别，子串一定是连续的，而子序列不一定是连续的
 */
public class LongestIncreasingSubsequence_LIS_300 {

    public static void main(String[] args) {
        // int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = new int[]{1, 4, 3, 4, 2, 3};

        System.out.println(new Solution().lengthOfLIS(nums));
    }

    /**
     * 二分查找解法
     * https://labuladong.github.io/algo/3/24/65/
     * patience game纸牌游戏
     */
    static class Solution1 {
        public int lengthOfLIS(int[] nums) {
            return 0;
        }

    }


    /**
     * https://labuladong.github.io/algo/3/24/65/
     * <p>
     * 运用数学归纳法的思想，假设 dp[0...i-1] 都已知，想办法求出 dp[i]，一旦这一步完成，整个题目基本就解决了。
     *
     * <p>
     * dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
     * <p>
     * 最终结果（子序列的最大长度）应该是 dp 数组中的最大值。
     * int res = 0;
     * for (int i = 0; i < dp.size(); i++) {
     * res = Math.max(res, dp[i]);
     * }
     * return res;
     * <p>
     * 计算每个 dp[i]:
     * 递增子序列，我们只要找到前面那些结尾比 dp[i] 小的子序列，
     * 然后把 dp[i] 接到最后，就可以形成一个新的递增子序列，而且这个新的子序列长度加一。
     * 可能形成很多种新的子序列，但是我们只选择最长的那一个，把最长子序列的长度作为 dp[i] 的值即可。
     * for (int j = 0; j < i; j++) {
     * if (nums[i] > nums[j])
     * dp[i] = Math.max(dp[i], dp[j] + 1);
     * }
     * <p>
     * O(N^2)
     */
    static class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            // 注意这里的初始化!
            Arrays.fill(dp, 1);
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        // 因为是从头开始遍历, 要取最大的那个
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                result = Math.max(result, dp[i]);
            }
            return result;
        }
    }


}
