package leetcode.dp;

/**
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，
 * 因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 */
public class DecodeWays_91 {
    public static void main(String[] args) {
        // String s = "11106";
        // String s = "11306";

        // String s = "113063";

//        String s = "27";
        String s = "2101";

        System.out.println(new Solution().numDecodings(s));
    }


    static class Solution {
        public int numDecodings(String s) {
            int length = s.length();
            // 初始化全是0
            int[] dp = new int[length];

            if (s.charAt(0) == '0') {
                return 0;
            }

            if (length == 1) {
                return 1;
            }

            // base case
            // i==0
            dp[0] = 1;
            // i==1
            if (s.charAt(1) != '0') {
                dp[1] += 1;
            }
            if (s.charAt(0) != '0' && calcPreAndCur(s, 1) <= 26) {
                dp[1] += 1;
            }


            for (int i = 2; i < length; i++) {
                // 一个字符
                if (s.charAt(i) != '0') {
                    dp[i] += dp[i - 1];
                }

                // 两个字符
                if (s.charAt(i - 1) != '0' && calcPreAndCur(s, i) <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[length - 1];
        }

        private int calcPreAndCur(String s, int i) {
            return (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
        }
    }


}
