package leetcode.island;
// 463 岛屿边长
public class IslandPerimeter_463 {
    public static void main(String[] args) {
        int[][] v1 = new int[][] {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};

        Solution solution = new Solution();
        solution.islandPerimeter(v1);
    }

    static class Solution {
        public int islandPerimeter(int[][] grid) {
            int result = 0;
            int rowLength = grid.length;
            int colLength = grid[0].length;
            boolean[][] isVisited = new boolean[rowLength][colLength];
            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < colLength; j++) {
                    // 未访问的岛屿
                    if (!isVisited[i][j] && grid[i][j] == 1) {
                        result = dfs(grid, i, j, isVisited);
                    }
                }
            }
            return result;
        }

        private int dfs(int[][] grid, int i, int j, boolean[][] isVisited) {
            // 计算周长，就根据dfs的情况来return
            // 如果是岛屿，那么他旁边的是海的话就+1
            // 如果旁边是边界的话+1
            // 如果旁边是岛屿的话+0
            // 注意虽然上面都说的是旁边，但是实际上是通过dfs来做的
            // 也就是说，直接判断grid[i][j]即可，用dfs+dfs+dfs+dfs来表示他的旁边
            // 对于每一个dfs就只需要判断自己即可

            if (!(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)) {
                // 边界 + 1
                return 1;
            }
            if (grid[i][j] == 0) {
                // 海 + 1
                return 1;
            }
            if (grid[i][j] == 1 && isVisited[i][j]) {
                // 已经遍历过的岛屿直接返回，不然又要跑到下面的dfs去了
                return 0;
            }

            isVisited[i][j] = true;

            return dfs(grid, i, j + 1, isVisited) +
                    dfs(grid, i, j - 1, isVisited) +
                    dfs(grid, i - 1, j, isVisited) +
                    dfs(grid, i + 1, j, isVisited);

        }
    }




}
