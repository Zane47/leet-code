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
        // String s = "2101";

        String s = "1201234";

        System.out.println(new Solution().numDecodings(s));
    }


    /**
     * i的状态与i-1和i-2有关, 所以不需要整个数组, 只需要3个元素保存即可
     */
    static class Solution {
        public int numDecodings(String s) {
            int length = s.length();
            if (s.charAt(0) == '0') {
                return 0;
            }

            if (length == 1) {
                return 1;
            }

            // base case
            // i==0
            int pre2 = 1;

            // i ==1
            int pre1 = 0;
            if (s.charAt(1) != '0') {
                pre1 += 1;
            }
            if (s.charAt(0) != '0' && calcPreAndCur(s, 1) <= 26) {
                pre1 += 1;
            }

            if (length == 2) {
                return pre1;
            }

            int cur = 0;
            for (int i = 2; i < length; i++) {
                cur = 0;
                // 一个字符
                if (s.charAt(i) != '0') {
                    cur += pre1;
                }

                // 两个字符
                if (s.charAt(i - 1) != '0' && calcPreAndCur(s, i) <= 26) {
                    cur += pre2;
                }

                // 更新
                pre2 = pre1;
                pre1 = cur;
            }

            return cur;
        }

        private int calcPreAndCur(String s, int i) {
            return (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
        }

    }


    /**
     * dp[i]: 以数字i结尾的decode的分组个数
     * <p>
     * 考虑最后一次解码用了几个字符
     * <p>
     * * 如果用了一个字符
     * <p>
     * 只要nums[i] != 0, 那么就说明就可以解码1 - 9,的字母, dp[i]就会在原来dp[i-1]的每一个组合后面添加数字nums[i]. dp[i] = dp[i-1]
     * <p>
     * * 如果有了两个字符
     * <p>
     * 如果nums[i-1] != 0, 并且nums[i-1]和nums[i]组成的两个数字 <= 26, 那么dp[i]就可以在dp[i-2]的每一组数字后面添加nums[i-1] * 10 + nums[i]. dp[i] = dp[i-2]
     * <p>
     * * ps:
     * <p>
     * 如果是0, 并且前一位的数组和0无法组成<=26的数字, 例如3,0
     * <p>
     * 那么dp[i] = 0. -> 不存在以0位结尾的decode组合. 所以这里dp数组初始化的时候就是0, 所以不需要再赋值了
     */
    static class Solution1 {
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
