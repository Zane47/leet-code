package leetcode.slidingwindow;

import java.util.HashSet;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {

    public static void main(String[] args) {
        // String s = "abcabcbb";
        // String s = "pwwkew";
        String s = "au";

        System.out.println(new Solution().lengthOfLongestSubstring(s));
    }





    /**
     * 暴力
     * 输入过长会超时
     */
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if ("".equals(s)) {
                return 0;
            }
            int max = 1;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j <= s.length(); j++) {
                    // 前开后闭
                    String subString = s.substring(i, j);
                    HashSet<Character> set = new HashSet<>();
                    for (int z = 0; z < subString.length(); z++) {
                        set.add(subString.charAt(z));

                    }
                    if (set.size() == subString.length()) {
                        max = Math.max(max, subString.length());
                    }
                }
            }

            return max;
        }
    }


}
