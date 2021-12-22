package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 每次你可以爬 1 或 2 个台阶
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {

        System.out.println(new Solution().climbStairs(2));

    }

    /**
     * 每次的状态都只与前面两次的状态有关, 所以不需要数组, 只需要两个变量保存前两次的即可
     */
    static class Solution3 {
        public int climbStairs(int n) {
            if (n == 0 || n == 1 || n == 2) {
                return n;
            }
            int result = 0;
            int pre1 = 1;
            int pre2 = 2;
            for (int i = 3; i <= n; i++) {
                result = pre1 + pre2;
                pre1 = pre2;
                pre2 = result;
            }
            return result;
        }
    }

    /**
     * dp, 每次你可以爬 1 或 2 个台阶
     * dp[n]有多少中爬法
     * dp[n]就是dp[n-1]爬一个台阶, dp[n-2]爬两个台阶, 这两个爬法加起来
     * f(n) = f(n-1) + f(n-2)
     */
    static class Solution {
        public int climbStairs(int n) {
            int[] step = new int[1000];
            step[0] = 0;
            step[1] = 1;
            step[2] = 2;
            for (int i = 3; i <= n; i++) {

                step[i] = step[i - 1] + step[i - 2];

            }
            return step[n];
        }
    }


    /**
     * 记忆化递归
     */
    static class Solution2 {
        Map<Integer, Integer> map = new HashMap<>();

        public int climbStairs(int n) {

            // 找递推关系式
            // 因为我们每次可以爬1or2个台阶，
            // 那么要到达第n层的台阶，要么是从n-1层爬一步上来的，要么是从n-2层爬两部上来的
            // 也即是说，f(n)取决于f(n-1)和f(n-2)
            // 但是超时，所以记忆化递归，空间换时间。

            int[] step = new int[Integer.MAX_VALUE];
            if (n == 1 || n == 2) {
                return n;
            }

            int result = 0;

            if (map.get(n) != null) {
                result = map.get(n);
            } else {
                result = climbStairs(n - 1) + climbStairs(n - 2);
                map.put(n, result);
            }
            return result;
        }
    }

    /**
     * 直接递归超时了
     * f(n) = f(n-1) + f(n-2)
     */
    static class Solution1 {
        public int climbStairs(int n) {

            if (n == 1 || n == 2) {
                return n;
            }

            return climbStairs(n - 1) + climbStairs(n - 2);


        }
    }


}
