package leetcode.dp;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class UglyNumber2_264 {
    public static void main(String[] args) {
        // [1, 2, 3, 4, 5, 6, 8, 9, 10, 12]
        int n = 10;
        System.out.println(new Solution().nthUglyNumber(n));
    }


    /**
     * dp
     */
    static class Solution {
        public int nthUglyNumber(int n) {

            int p2 = 1;
            int p3 = 1;
            int p5 = 1;

            int[] dp = new int[n + 1];
            dp[1] = 1;

            for (int i = 2; i < n + 1; i++) {
                dp[i] = Math.min(Math.min(2 * dp[p2], 3 * dp[p3]), 5 * dp[p5]);

                if (dp[i] == 2 * dp[p2]) {
                    p2++;
                }
                if (dp[i] == 3 * dp[p3]) {
                    p3++;
                }
                if (dp[i] == 5 * dp[p5]) {
                    p5++;
                }
            }

            return dp[n];
        }
    }


    /**
     * 最小堆
     * 维护一个最小堆, 初始化加入1, 然后去除堆顶元素(min),
     * 然后将对应的`2*min`, `3*min`和`5*min`分别加入到堆中, 一直到弹出n个.
     * <p>
     * 注意有可能有重复的, 所以使用set来去重
     * <p>
     * int不够, 用long
     */
    static class Solution1 {
        public int nthUglyNumber(int n) {
            int result = 0;

            // 默认小顶堆
            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            minHeap.offer(1L);

            Set<Long> set = new HashSet<>();
            set.add(1L);

            while (n != 0) {
                long temp = minHeap.poll();

                result = (int) temp;

                long v1 = temp * 2;
                long v2 = temp * 3;
                long v3 = temp * 5;

                if (!set.contains(v1)) {
                    minHeap.offer(v1);
                    set.add(v1);
                }

                if (!set.contains(v2)) {
                    minHeap.offer(v2);
                    set.add(v2);
                }

                if (!set.contains(v3)) {
                    minHeap.offer(v3);
                    set.add(v3);
                }

                n--;
            }


            return result;
        }
    }
}
