package LeetCode.dp;


import java.util.Scanner;

/**
 * longestPalindrome
 * 最长回文子串
 *
 *
 *
 */

public class LongestPalindrome_5 {


    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(solution.longestPalindrome(input));
    }




    // dp
    // dp[i][j]表示s[i]到s[j]所表示的子串是否是回文串，是则1，否则0
    /**
     * 如果s[i] == s[j]，那么只需要s[i+1]至s[j-1]是回文串，那么s[i]到s[j]也就是回文子串，反之则反
     * 如果s[i]!=s[j]，那么s[i]至s[j]就一定不是回文子串
     * 所以状态方程为：
     * dp =
     *
     */
    static class Solution {
        public String longestPalindrome(String s) {
            String result = "";







            return result;
        }
    }





    // 暴力方法
    static class SolutionBrute {
        public String longestPalindrome(String s) {
            int length = s.length();
            int max = 0;
            String result = "";
            if (length == 1) {
                return s;
            }
            for (int i = 0; i < length; i++) {
                // 需要注意这里必须是<=
                for (int j = i + 1; j <= length; j++) {
                    // 所有子串
                    String subStr = s.substring(i, j);
                    int subLen = subStr.length();
                    if (isPalindrome(subStr) && max < subLen) {
                        max = subLen;
                        result = subStr;
                    }
                }
            }


            return result;
        }

        private boolean isPalindrome(String subStr) {
            int len = subStr.length();
            for (int i = 0; i < len; i++) {
                if (subStr.charAt(i) != subStr.charAt(len - i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }



}
