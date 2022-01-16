package leetcode.structure.stack;


import java.util.Stack;

/**
 * [1,2,3,4,5]
 * [4,5,3,2,1]
 */
public class ValidateStackSequences_946 {
    public static void main(String[] args) {
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 5, 3, 2, 1};

        System.out.println(new Solution().validateStackSequences(pushed, popped));
    }


    static class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {

            // 栈
            Stack<Integer> stack = new Stack<>();

            // 记录pop位置
            int j = 0;
            // push进去, 如果栈顶和pop相同, 则pop
            for (int push : pushed) {
                stack.push(push);
                while (!stack.isEmpty() && stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                }
            }


            return stack.isEmpty();
        }
    }
}
