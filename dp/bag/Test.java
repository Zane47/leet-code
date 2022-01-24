package leetcode.dp.bag;

import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 24+18/3 - 2 * 5
 * 先算乘除, 然后替换原来的位置即可
 * <p>
 * 1. 怎么算? ->
 * <p>
 * <p>
 * +:
 * -:
 * *,/:
 * <p>
 * <p>
 * stack
 */
public class Test {
    public static void main(String[] args) {
        String input = "10+18/3 - 2 * 5";//20
        // stack存储计算过程
        Deque<Integer> stack = new LinkedList<>();
        char preCharacter = '+';
        int temp = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c) && c != ' ') {
                updateStack(stack, temp, preCharacter);
                preCharacter = c;
                temp = 0;
            }
            if (Character.isDigit(c)) {
                temp = temp * 10 + c - '0';
            }
        }
        // 计算最后一个数字
        updateStack(stack, temp, preCharacter);
        // sum
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        System.out.println(result);

    }

    private static void updateStack(Deque<Integer> stack, int temp, char pre) {
        if (pre == '+') {
            stack.push(temp);
        } else if (pre == '-') {
            stack.push(-temp);
        } else if (pre == '*') {
            int v1 = stack.pop() * temp;
            stack.push(v1);
        } else if (pre == '/') {
            int v1 = stack.pop() / temp;
            stack.push(v1);
        }

    }
}
