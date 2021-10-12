package leetcode.dp;


public class FibonacciNumber_509 {

    public static void main(String[] args) {

        System.out.println(new Solution().fib(10));


    }

    /**
     * 观察可以发现, 实际上不需要用数组来存储
     * <p>
     * 当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，
     * 只要想办法存储之前的两个状态就行了
     */
    static class Solution {
        public int fib(int n) {
            if (n == 0 || n == 1) {
                return n;
            }
            int prev = 0;
            int curr = 1;
            for (int i = 2; i <= n; i++) {
                int sum = prev + curr;
                prev = curr;
                curr = sum;
            }

            return curr;
        }
    }

    /**
     * dp, 自底向上
     */
    static class Solution3 {
        public int fib(int n) {
            if (n == 0 || n == 1) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }


    /**
     * 带备忘录的递归
     */
    static class Solution2 {
        public int fib(int n) {
            int[] memo = new int[n + 1];
            return helper(n, memo);
        }

        private int helper(int n, int[] memo) {
            if (n == 0 || n == 1) {
                return n;
            }
            if (memo[n] != 0) {
                return memo[n];
            }
            memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
            return memo[n];
        }

    }

    /**
     * f(n) = f(n-1) + f(n-2)
     * 自顶向下, 递归
     */
    static class Solution1 {
        public int fib(int n) {
            if (n == 0 || n == 1) {
                return n;
            }
            return fib(n - 1) + fib(n - 2);
        }
    }


}
