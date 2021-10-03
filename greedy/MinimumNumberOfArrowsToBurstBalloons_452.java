package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * -> 435类似
 *
 *
 */
public class MinimumNumberOfArrowsToBurstBalloons_452 {
    public static void main(String[] args) {
        /*int[][] points = new int[][]{
                {10,16},{2,8},{1,6},{7,12}
        };*/

        // [[-2147483646,-2147483645],[2147483646,2147483647]]
        // 2
        // -2147483645 - 2147483647 Numeric overflow in expression
        int[][] points = new int[][]{
                {-2147483646, -2147483645},{2147483646, 2147483647}
        };
        System.out.print(new Solution().findMinArrowShots(points));
    }

    /**
     *
     * 其实和区间调度算法一模一样
     * 如果有n个不重叠的区间, 那么就至少需要n个箭来射爆气球
     * 只不过这里按照题目意思, 端点部分也可以射爆两个气球, 也就是说端点相同也算重叠, x1_end = x2_start
     * 求有多少个不重叠部分
     */
    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // 先排序, 按照右坐标升序
            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // -2147483645 - 2147483647 Numeric overflow in expression
                    // return o1[1] - o2[1];
                    if (o1[1] < o2[1]) {
                        return -1;
                    } else if (o1[1] > o2[1]) {
                        return 1;
                    } else {
                        // o1[1] == o2[1]
                        return 0;
                    }
                }
            });

            int count = 1;
            int xEnd = points[0][1];

            for (int[] point : points) {
                // 没有等号
                if (xEnd < point[0]) {
                    count++;
                    xEnd = point[1];
                }
            }
            return count;
        }
    }
}
