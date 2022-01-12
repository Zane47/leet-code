package leetcode.dp.bag;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/92/description
 * <p>
 * <p>
 * 描述
 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 * 你不可以将物品进行切割。
 * 数组 = [3,4,8,5]
 * backpack size = 10
 * -> 9
 * <p>
 * 数组 = [2,3,5,7]
 * backpack size = 12
 * -> 12
 * <p>
 * 两种思路
 * <p>
 * dp[i][j]表示前i个数里是否能凑出j的和,true/false
 * dp[i][j]表示前i个数能否凑出的<=j的最大和是多少
 */
public class BackPack_LintCode92 {
    public static void main(String[] args) {
        int[] A = {3, 4, 8, 5};
        System.out.println(new Solution().backPack(10, A));

    }

    /**
     * dp[i][v] : 前i个数能否凑出来和为v, true, false
     * 最后倒序输出第一个为true的数字, 即为可以凑出的最大数字
     */
    static class SolutionBoolean {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A: Given n items with size A[i]
         * @return: The maximum size
         */
        public int backPack(int m, int[] A) {
            // write your code here
            int n = A.length;
            boolean[][] dp = new boolean[n + 1][m + 1];

            // 初始化
            // 第一行dp[0][...]为false
            Arrays.fill(dp[0], false);
            // 第一列dp[...][0]为true
            for (int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= n; i++) {
                for (int v = 1; v <= m; v++) {
                    if (A[i - 1] > v) {
                        dp[i][v] = dp[i - 1][v];
                    } else {
                        // 要么前i-1个数中已经凑出了v的和
                        // 要么前i-1个数凑出了v-nums[i]的和, 那么这次直接加上nums[i]
                        dp[i][v] = dp[i - 1][v] || dp[i - 1][v - A[i - 1]];
                    }
                }
            }

            for (int v = m; v >= 0; v--) {
                if (dp[n][v]) {
                    return v;
                }
            }

            return -1;
        }
    }

    /**
     * dp[i][j]:前i个数里挑出若干个数总和<=j的最大和是多少
     * 这个方法更加直观一些
     * 但是这个方法的速度比dp表示truefalse的方法慢
     * 因为||运算比+运算要快
     */
    static class Solution {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A: Given n items with size A[i]
         * @return: The maximum size
         */
        public int backPack(int m, int[] A) {
            int n = A.length;
            int[][] dp = new int[n + 1][m + 1];

            // 初始化
            // dp[0][v]: 前0个的和
            Arrays.fill(dp[0], 0);
            // dp[v][0]: 容量为0的时候
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }

            for (int i = 1; i <= n; i++) {
                for (int v = 1; v <= m; v++) {
                    if (A[i - 1] > v) {
                        dp[i][v] = dp[i - 1][v];
                    } else {
                        dp[i][v] = Math.max(
                                dp[i - 1][v],
                                dp[i - 1][v - A[i - 1]] + A[i - 1]);
                    }
                }
            }
            return dp[n][m];
        }
    }

}
