package leetcode.dfsandbfs.island;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands_200_2 {
    class Solution {
        public int numIslands(char[][] grid) {
            int result = 0;
            int row = grid.length;
            int col = grid[0].length;
            boolean[][] isVisited = new boolean[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1' && isVisited[i][j] == false) {
                        bfs(grid, i, j, isVisited);
                        result++;
                    }
                }
            }

            return result;
        }

        private void bfs(char[][] grid, int i, int j, boolean[][] isVisited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {i, j});
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                if (x>=0 && x<grid.length && y>=0 && y <grid[0].length && grid[x][y]=='1' && isVisited[x][y] == false) {
                    isVisited[x][y] = true;
                    queue.offer(new int[]{x-1, y});
                    queue.offer(new int[]{x+1, y});
                    queue.offer(new int[]{x, y-1});
                    queue.offer(new int[]{x, y+1});
                }
            }
        }
    }



    class Solution2 {
        public int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int result = 0;
            boolean[][] isVisited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1' && isVisited[i][j] == false) {
                        dfs(grid, i, j, isVisited);
                        result++;
                    }

                }
            }
            return result;
        }

        private void dfs(char[][] grid, int i, int j, boolean[][] isVisited) {
            if (i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j] == '1' && isVisited[i][j] == false) {
                isVisited[i][j] = true;
                dfs(grid, i+1, j, isVisited);
                dfs(grid, i-1, j, isVisited);
                dfs(grid, i, j-1, isVisited);
                dfs(grid, i, j+1, isVisited);
            }
        }

    }

}


