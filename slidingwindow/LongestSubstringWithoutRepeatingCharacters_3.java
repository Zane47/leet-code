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
        String s = "abcabcbb";

        System.out.println(new Solution().lengthOfLongestSubstring(s));
    }

    /**
     * 滑动窗口
     * 什么是滑动窗口？
     *
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     *
     * 如何移动？
     *
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     *
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     */
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            // 记录字符是否出现过
            HashSet<Character> hashSet = new HashSet<>();
            int right = 0;
            int result = 0;
            for (int left = 0; left < s.length(); left++) {
                // 移除一个字符, 左指针向右移动一格
                if (left != 0) {
                    hashSet.remove(s.charAt(left - 1));
                }
                while (right < s.length() && !hashSet.contains(s.charAt(right))) {
                    hashSet.add(s.charAt(right));
                    right++;
                }
                result = Math.max(result, hashSet.size());
            }

            return result;
        }
    }


    /**
     * 暴力
     * set
     * 输入过长 超时
     */
    static class Solution1 {
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
