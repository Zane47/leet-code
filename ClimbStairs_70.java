package LeetCode;

import java.util.HashMap;

// 70. 爬楼梯
public class ClimbStairs_70 {

    // 滚动数组
    static class Solution {
        public int climStairs(int n) {

            return 0;
        }
    }


    // 递归解法
    static class Solution1 {
        private HashMap<Integer, Integer> map1 = new HashMap<>();
        public int climbStairs(int n) {
            // 找递推关系式
            // 因为我们每次可以爬1or2个台阶，
            // 那么要到达第n层的台阶，要么是从n-1层爬一步上来的，要么是从n-2层爬两部上来的
            // 也即是说，f(n)取决于f(n-1)和f(n-2)
            // 但是超时，所以记忆化递归，空间换时间。
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            int result = 0;
            if (map1.get(n) != null) {
                result = map1.get(n);
            }
            else {
                result = climbStairs(n - 1) + climbStairs(n - 2);
                map1.put(n, result);
            }
            return result;
        }
    }
}
