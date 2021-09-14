package leetcode.simulation;

/**
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 *
 *
 *
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 *
 */

public class Base7_504 {
    public static void main(String[] args) {


        System.out.println(new Solution().convertToBase7(100));
        System.out.println(new Solution().convertToBase7(-10));
    }


    static class Solution {
        public String convertToBase7(int num) {
            StringBuilder sb = new StringBuilder();
            boolean isNegative = false;
            if (num < 0) {
                num = -num;
                isNegative = true;
            }

            if (num == 0) {
                return "0";
            }

            while (num != 0) {
                int v1 = num % 7;
                sb.append(v1);
                num = num / 7;
            }
            if (isNegative) {
                sb.append("-");
            }
            return sb.reverse().toString();

        }
    }

}
