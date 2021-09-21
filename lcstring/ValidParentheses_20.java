package leetcode.lcstring;

import java.util.*;

/**
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 *
 * 示例3：
 * 输入：s = "(]"
 * 输出：false
 *
 *
 * 示例4:
 * 输入：s = "([)]"
 * 输出：false
 *
 *
 * 示例5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ValidParentheses_20 {

    public static void main(String[] args) {
        // ()[]{}
        // String s = "([)]";
        String s = "()";
        System.out.println(new Solution().isValid(s));

    }


    /**
     * 快一点的方法
     */
    static class Solution {
        public boolean isValid(String s) {
            if (s.isEmpty()) {
                return true;
            }
            if (s.length() % 2 != 0) {
                return false;
            }
            Map<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put('}', '{');
            map.put(']', '[');


            // 这个更快！
            Deque<Character> stack = new LinkedList<Character>();


            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // 右括号
                if (map.containsKey(c)) {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    Character pop = stack.pop();
                    if (!map.get(c).equals(pop)) {
                        return false;
                    }
                }
                // 左括号
                if (map.containsValue(c)) {
                    stack.push(c);
                }


            }
            return stack.isEmpty();

        }


    }



    /**
     * 用栈来模拟
     * 这个方法3ms太慢了
     */
    static class Solution1 {
        public boolean isValid(String s) {
            Map<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('{', '}');
            map.put('[', ']');
            map.put(')', '(');
            map.put('}', '{');
            map.put(']', '[');

            // 其实可以直接使用map.contains和
            String s1 = "({[";
            String s2 = ")}]";

            Stack<Character> stack = new Stack<>();
            // 遍历，遇到左括号的就入栈，碰到对应的就弹出
            for (int i = 0; i < s.length(); i++) {
                // 左括号类就入栈
                char c = s.charAt(i);
                if (s1.contains(String.valueOf(c))) {
                    stack.push(c);
                }
                // 右括号就看看栈顶是不是对应的左括号
                if (s2.contains(String.valueOf(c))) {
                    // 空的话就不要弹出了，直接返回false
                    if (stack.isEmpty()) {
                        return false;
                    }
                    // 现在的括号
                    Character popC = stack.pop();
                    // 理应对应的括号
                    Character character = map.get(c);
                    if (!popC.equals(character)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }







}
