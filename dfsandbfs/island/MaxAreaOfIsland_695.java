package leetcode.dfsandbfs.island;

public class MaxAreaOfIsland_695 {

    public static void main(String[] args) {
        /*int[][] grid = new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
*/

        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
        Solution solution = new Solution();
        System.out.println(solution.maxAreaOfIsland(grid));
    }

    static class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            int rowLength = grid.length;
            int colLength = grid[0].length;
            boolean[][] isVisited = new boolean[rowLength][colLength];
            for (int i = 0; i < rowLength; i++) {
               for (int j = 0; j < colLength; j++) {
                   if (grid[i][j] == 1 && !isVisited[i][j]) {
                       max = Math.max(dfs(grid, i, j, isVisited), max);
                   }
               }
            }

            return max;
        }

        private int dfs(int[][] grid, int i, int j, boolean[][] isVisited) {
            // 最大岛屿数量，遍历到了一个就+1
            // 边界的时候就推出
            if (!(i >= 0 && i < grid.length && j >=0 && j < grid[0].length)) {
                return 0;
            }

            // 水的时候就退出
            if (grid[i][j] == 0) {
                return 0;
            }

            // 访问过的岛屿
            if (grid[i][j] == 1 && isVisited[i][j]) {
                return 0;
            }

            isVisited[i][j] = true;
            // 每次遍历到一个新的，都+1
            return 1 +
                    dfs(grid, i, j + 1, isVisited) +
                    dfs(grid, i, j - 1, isVisited) +
                    dfs(grid, i - 1, j, isVisited) +
                    dfs(grid, i + 1, j, isVisited);

        }
    }

}
