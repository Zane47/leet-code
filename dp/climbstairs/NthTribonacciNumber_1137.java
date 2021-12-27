package leetcode.dp.climbstairs;

/**
 * Tn+3 = Tn + Tn+1 + Tn+2
 * -> Tn = Tn-3+Tn-2+Tn-1
 * n: [0, 37]
 * 答案保证是32位正数, <=2^32 - 1
 */
public class NthTribonacciNumber_1137 {
    public static void main(String[] args) {
        System.out.println(new Solution().tribonacci(25));
    }

    /**
     * 每一个dp状态至于前三个数字有关, 不需要数组
     */
    static class Solution {
        public int tribonacci(int n) {
            if (n == 0 || n == 1) {
                return n;
            }
            if (n == 2) {
                return 1;
            }

            int result = 0;
            int pre1 = 0;
            int pre2 = 1;
            int pre3 = 1;

            for (int i = 3; i <= n; i++) {
                result = pre1 + pre2 + pre3;
                pre1 = pre2;
                pre2 = pre3;
                pre3 = result;
            }

            return result;
        }
    }

    /**
     * dp数组
     */
    static class Solution1 {
        public int tribonacci(int n) {
            if (n == 0 || n == 1) {
                return n;
            }
            if (n == 2) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }

            return dp[n];
        }
    }
}
