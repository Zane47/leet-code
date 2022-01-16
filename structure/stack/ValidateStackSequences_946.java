package leetcode.structure.stack;


import java.util.Stack;

/**
 * [1,2,3,4,5]
 * [4,5,3,2,1]
 * pop序列是否是push序列
 *
 * 遍历push序列, 只要和pop序列不一致就push, pop和栈顶一致就pop,
 * 最后看是否栈空
 *
 * 将 pushed 队列中的每个数都 push 到栈中，同时检查这个数是不是 popped 序列中下一个要 pop 的值，如果是就把它 pop 出来。
 *
 * 最后，检查不是所有的该 pop 出来的值都是 pop 出来了。
 *
 *
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
