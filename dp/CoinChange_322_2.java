package leetcode.dp;


import java.lang.reflect.Array;
import java.rmi.ConnectIOException;
import java.util.Arrays;

/**
 * 把所有可能的凑硬币方法都穷举出来，然后找找看最少需要多少枚硬币。
 * -> pdd的第一题, 切绳子, 这里是要总数, pdd的是要具体的每一个的个数
 */
public class CoinChange_322_2 {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;

        /*int[] coins = new int[]{2};
        int amount = 3;*/

        System.out.print(new Solution().coinChange(coins, amount));
    }

    // ------------------------ 自底向上 ------------------------

    /**
     * 自底向上
     */
    static class Solution {
        public int coinChange(int[] coins, int amount) {
            // 最多就是amount个, 全是1
            int[] dp = new int[amount + 1];
            // 初始化一个大值, IntegerMaxValue会出界
            Arrays.fill(dp, amount + 10);

            dp[0] = 0;


            // 自底向上
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }

            // 如果凑不出来就返回-1
            if (dp[amount] == amount + 10) {
                return -1;
            } else {
                return dp[amount];
            }
        }
    }

    // ------------------------ 自顶向下 ------------------------

    /**
     * 使用memo
     *
     * [186,419,83,408]
     * 6249
     */
    static class Solution3 {
        int[] memo;

        public int coinChange(int[] coins, int amount) {
            // 初始化memo
            memo = new int[amount + 1];
            Arrays.fill(memo, -2);

            return func(coins, amount);
        }

        private int func(int[] coins, int n) {
            if (n < 0) {
                return -1;
            }
            if (n == 0) {
                return 0;
            }

            if (memo[n] != -2) {
                return memo[n];
            }

            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProb = func(coins, n - coin);
                if (subProb >= 0) {
                    result = Math.min(result, subProb + 1);
                }
            }

            if (result == Integer.MAX_VALUE) {
                memo[n] = -1;
                return -1;
            } else {
                // 计算结果存入memo
                memo[n] = result;
                return result;
            }
        }

    }


    /**
     * 不带memo
     * [1,2,5]
     * 100
     * 超时
     */
    static class Solution2 {
        public int coinChange(int[] coins, int amount) {
            return func(coins, amount);
        }

        private int func(int[] coins, int n) {
            if (n < 0) {
                return -1;
            }
            // base case
            if (n == 0) {
                return 0;
            }
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 凑出n-coin的硬币数量的子问题
                int subProb = func(coins, n - coin);
                // 有解
                if (subProb >= 0) {
                    result = Math.min(result, subProb + 1);
                }
            }

            if (result == Integer.MAX_VALUE) {
                return -1;
            } else {
                return result;
            }
        }

    }


}
