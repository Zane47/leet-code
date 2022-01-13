package leetcode.math;

/**
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 丑数 就是只包含质因数2、3 和/或5的正整数。
 */
public class UglyNumber_263 {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(new Solution().isUgly(n));
    }


    /**
     * 数学, 乘法具有交换律, 所以先除以哪个都可以
     */
    static class Solution {
        public boolean isUgly(int n) {
            if (n <= 0) {
                return false;
            }

            while (n % 2 == 0) {
                n /= 2;
            }
            while (n % 3 == 0) {
                n /= 3;
            }
            while (n % 5 == 0) {
                n /= 5;
            }

            return n == 1;
        }
    }
}
