package leetcode.structure.stack;

import java.util.Stack;

/**
 * 打字模拟
 * < : backspace
 */
public class TextContent_LintCode299 {
    public static void main(String[] args) {
        System.out.println(new Solution().getTextcontent("aabbcc<<<dd"));
    }

    static class Solution {
        /**
         * @param s: A string
         * @return: A string
         */
        public String getTextcontent(String s) {
            // write your code here.
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '<') {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(s.charAt(i));
                }
            }

            String result = "";
            while (!stack.isEmpty()) {
                Character temp = stack.peek();
                result = temp + result;
                stack.pop();
            }
            return result;
        }
    }
}
