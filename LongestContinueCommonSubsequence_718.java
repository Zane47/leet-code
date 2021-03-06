package leetcode;
/** 重要！！！
 * "本题与公共子序列（LongestCommonSubsequence）不同，子序列不一定都是连续的，
 * 只要前面有相同的子序列，哪怕当前比较的字符不一样，那么当前字符串之前的子序列也不会为 0。
 * 而子串(子数组)是连续的，若当前比较的字符不相同，
 * 则当前位置的最长公共子数组(子串)的长度为 0，即 dp[i][j] = 0(就是没有)。"
 */


/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *  
 *
 * 提示：
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */


public class LongestContinueCommonSubsequence_718 {

    public static void main(String[] args) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};

        Solution solution = new Solution();
        solution.findLength(A, B);

    }


    static class Solution {
        public int findLength(int[] A, int[] B) {



            return 0;
        }
    }



    // dp解法
    static class Solution1 {
        public int findLength(int[] A, int[] B) {
            int[][] dp = new int[A.length+1][B.length+1];
            // dp[i][j]：表示第一个数组 A 前 i 个元素和数组 B 前 j 个元素组成的最长公共子数组(相当于子串)的长度。

            /**
             * 若当前两个元素值相同，即 A[i] == B[j]，
             * 则说明当前元素可以构成公共子数组，
             * 所以还要加上它们的前一个元素构成的最长公共子数组的长度(在原来的基础上加 1)，
             * 此时状态转移方程：dp[i][j] = dp[i - 1][j - 1] + 1。
             *
             *
             * 若当前两个元素值不同，即 A[i] != B[j]，
             * 则说明当前元素无法构成公共子数组(就是：当前元素不能成为公共子数组里的一员)。
             * 因为公共子数组必须是连续的，而此时的元素值不同，相当于直接断开了，
             * 此时状态转移方程：dp[i][j] = 0。
             *
             *  "本题与公共子序列不同，子序列不一定都是连续的，只要前面有相同的子序列，哪怕当前比较的字符不"
             * "一样，那么当前字符串之前的子序列也不会为 0。而子串(子数组)是连续的，若当前比较的字符不相同，"
             * "则当前位置的最长公共子数组(子串)的长度为 0，即 dp[i][j] = 0(就是没有)。"
             */


            // 必须要小于等于，因为双for循环将是从1开始
            // 或者你下面的双for循环是从后往前的[n-1, 0], [m-1, 0]
            int max = 0;
            for (int i = 1; i <= A.length; i++) {
                for (int j = 1; j <= B.length; j++) {
                    if (A[i-1] == B[j-1]) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    else {
                        dp[i][j] = 0;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }










}




