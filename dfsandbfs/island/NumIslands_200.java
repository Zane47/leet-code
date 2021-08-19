package leetcode.dfsandbfs.island;

import java.util.LinkedList;
import java.util.Queue;

// 200，岛屿数量
public class NumIslands_200 {

    public static void main(String[] args) {

        Solution solution = new Solution();
        char[][] v1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };

        solution.numIslands(v1);
    }


    // BFS
    static class Solution {
        public int numIslands(char[][] grid) {
            int numberOfIslands = 0;
            int rowLength = grid.length;
            int colLength = grid[0].length;
            boolean[][] isVisited = new boolean[rowLength][colLength];
            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < colLength; j++) {
                    if (grid[i][j] == '1' && isVisited[i][j] == false) {
                        bfs(grid, i, j, isVisited);
                        numberOfIslands++;
                    }
                }
            }


            return numberOfIslands;
        }

        private void bfs(char[][] grid, int i, int j, boolean[][] isVisisted) {
            // 队列中存放位置，i,j 为1的位置
            Queue<int[]> queue = new LinkedList<>();

            queue.add(new int[]{i, j});
            while (!queue.isEmpty()) {
                int[] current = queue.remove();
                int tempi = current[0];
                int tempj = current[1];
                // 判断边界
                if (tempi >= 0 && tempi <= grid.length && j >= 0 && j <= grid[0].length &&
                        grid[tempi][tempj] == '1' && !isVisisted[tempi][tempj]) {
                    // 要置成已访问，不然chaoshi
                    isVisisted[tempi][tempj] = true;
                    // 把上下左右符合的点加入
                    queue.add(new int[]{tempi, tempj + 1});
                    queue.add(new int[]{tempi, tempj - 1});
                    queue.add(new int[]{tempi - 1, tempj});
                    queue.add(new int[]{tempi + 1, tempj});
                }
            }
        }
    }

    // DFS
    static class Solution1 {
        public int numIslands(char[][] grid) {
            int numberOfIsland = 0;
            int rowLength = grid.length;
            int colLength = grid[0].length;
            // 是否访问过这个岛屿，
            boolean[][] visited = new boolean[rowLength][colLength];

            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < colLength; j++) {
                    // 未访问过并且是岛屿
                    if (visited[i][j] == false) {
                        if (grid[i][j] == '1') {
                            // 如果是岛屿的话，就深度优先遍历，找到他上下左右的是否有1，并且未访问过
                            dfs(grid, i, j, visited);
                            // 全部深度遍历完了之后，那就是一个岛屿了
                            numberOfIsland++;
                        }

                    }
                }
            }
            return numberOfIsland;
        }

        private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
            // 先污染后治理”的方法，先做递归调用，再在每个 DFS 函数的开头判断坐标是否合法，不合法的直接返回。
            // 某些情况，例如到达边界了那就
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
                    visited[i][j] || grid[i][j] == '0') {
                return;
            }
            visited[i][j] = true;

            // 深度遍历他的上下左右
            dfs(grid, i, j+1, visited);
            dfs(grid, i, j-1, visited);
            dfs(grid, i-1, j, visited);
            dfs(grid, i+1, j, visited);
        }
    }
}
