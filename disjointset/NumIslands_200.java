package leetcode.disjointset;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands_200 {

    public static void main(String[] args) {

        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'},
        };
        System.out.println(new Solution().numIslands(grid));
    }

    /**
     * 并查集
     *
     * 并查集代替搜索
     */
    static class Solution {
        public int numIslands(char[][] grid) {
            // grid可能是3*4，长宽不一致的情况
            int m = grid.length;
            int n = grid[0].length;
            // 摊开来做
            UnionFind uf = new UnionFind(grid);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        //岛屿沉没代替isVisited
                        grid[i][j] = '0';
                        if (i - 1 >= 0 && grid[i-1][j] == '1') {
                            uf.unionMerge(i*n+j, (i-1)*n+j);
                        }
                        if (i + 1 < m && grid[i+1][j] == '1') {
                            uf.unionMerge(i*n+j, (i+1)*n+j);
                        }
                        if (j - 1 >= 0 && grid[i][j-1] == '1') {
                            uf.unionMerge(i*n+j, i*n+j-1);
                        }
                        if (j + 1 < n && grid[i][j+1] == '1') {
                            uf.unionMerge(i*n+j, i*n+j+1);
                        }
                    }
                }
            }

            return uf.getSize();

        }

        class UnionFind {
            int[] roots;
            // 深度
            int[] rank;
            int size;

            public UnionFind(char[][] grid) {
                size = 0;
                int m = grid.length;
                int n = grid[0].length;
                roots = new int[m*n];
                rank = new int[m*n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == '1') {
                            roots[i*n+j] = i*n+j;
                            size++;
                        }
                        rank[i*n+j] = 0;
                    }
                }
            }

            public int findRoot(int i) {
                if (roots[i] == i) {
                    return i;
                } else {
                    roots[i] = findRoot(roots[i]);
                    return roots[i];
                }

                /*while (i != roots[i]) {
                    roots[i] = roots[roots[i]];
                    i = roots[i];

                }
                return i;*/
            }


            public void unionMerge(int x, int y) {
                int rootX = findRoot(x);
                int rootY = findRoot(y);
                if (rootX != rootY) {
                    if (rank[rootX] > rank[rootY]) {
                        roots[rootY] = rootX;
                    } else if (rank[rootX] < rank[rootY]) {
                        roots[rootX] = rootY;
                    } else {
                        roots[rootY] = rootX;
                        rank[rootX]++;
                    }
                    size--;
                }


            }

            public int getSize() {
                return this.size;
            }



        }

    }




    class Solution2 {
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



    class Solution1 {
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


