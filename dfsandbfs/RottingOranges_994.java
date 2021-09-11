package leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges_994 {

    public static void main(String[] args) {

        int[][] grid = new int[][] {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println(new Solution().orangesRotting(grid));
    }

    /**
     * 腐烂过程就是bfs的过程
     *
     * 但是注意可能有多个橘子同时开始腐烂，并且每一个格子都有可能有腐烂的橘子
     */
    static class Solution {
        public int orangesRotting(int[][] grid) {
            int rowLength = grid.length;
            int colLength = grid[0].length;
            int freshCount = 0;
            // 存储腐烂橘子位置
            Queue<int[]> queue = new LinkedList<>();
            // 先计算新鲜橘子的个数和腐烂橘子的位置
            // 个数用来看最后是否返回-1，位置用来做腐烂的过程，因为腐烂橘子的位置和个数随机
            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < colLength; j++) {
                    if (grid[i][j] == 1) {
                        freshCount++;
                    } else if (grid[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            // 模拟腐烂过程
            int min = 0;
            while (freshCount > 0 && !queue.isEmpty()) {
                // 根据queue的大小来表示一分钟的时候有多少个橘子同时开始腐烂
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    // 腐烂橘子的坐标
                    int[] rotPosition = queue.poll();
                    int x = rotPosition[0];
                    int y = rotPosition[1];
                    // 腐烂四周的橘子
                    if (x - 1 >= 0 && grid[x-1][y] == 1) {
                        grid[x - 1][y] = 2;
                        freshCount--;
                        queue.offer(new int[] {x-1, y});
                    }
                    if (x + 1 < rowLength && grid[x+1][y] == 1) {
                        grid[x + 1][y] = 2;
                        freshCount--;
                        queue.offer(new int[] {x+1, y});
                    }
                    if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                        grid[x][y - 1] = 2;
                        freshCount--;
                        queue.offer(new int[] {x, y-1});
                    }
                    if (y + 1 < colLength && grid[x][y + 1] == 1) {
                        grid[x][y+1] = 2;
                        freshCount--;
                        queue.offer(new int[] {x, y+1});
                    }
                }
                // 一分钟可能有多个橘子同时开始感染其他橘子，这里需要用for
                min++;
            }


            // 这里-1是因为最后一轮的时候，已经全部腐烂了，就不需要再算时间了，但是上面代码最后还是++，需要手动减掉
            // 但是这里是不对的，因为如果出现了只有一个0，或者全是0的情况就不对了
            // [0], [[0,0,0,0]]
            return freshCount == 0 ? min : -1;

        }
    }

}
