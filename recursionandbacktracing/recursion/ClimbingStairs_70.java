package leetcode.recursionandbacktracing.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 每次你可以爬 1 或 2 个台阶
 *
 *
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {

        System.out.println(new Solution().climbStairs(2));

    }


    /**
     * dp
     *
     *
     *
     */

    /**
     * 记忆化递归
     *
     */
    static class Solution {
        Map<Integer, Integer> map = new HashMap<>();

        public int climbStairs(int n) {

            // 找递推关系式
            // 因为我们每次可以爬1or2个台阶，
            // 那么要到达第n层的台阶，要么是从n-1层爬一步上来的，要么是从n-2层爬两部上来的
            // 也即是说，f(n)取决于f(n-1)和f(n-2)
            // 但是超时，所以记忆化递归，空间换时间。

            int[] step = new int[Integer.MAX_VALUE];
            if (n == 1 || n ==2) {
                return n;
            }

            int result= 0;

            if (map.get(n) != null) {
                result = map.get(n);
            } else {
                result = climbStairs(n-1) + climbStairs(n-2);
                map.put(n, result);
            }
            return result;
        }
    }

    /**直接递归超时了
     * f(n) = f(n-1) + f(n-2)
     */
    static class Solution1 {
        public int climbStairs(int n) {

            if (n == 1 || n ==2) {
                return n;
            }

            return climbStairs(n-1) + climbStairs(n-2);


        }
    }




}
