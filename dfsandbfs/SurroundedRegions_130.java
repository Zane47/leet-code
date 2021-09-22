package leetcode.dfsandbfs;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 *
 * 输入：board =
 * [["X","X","X","X"],
 * ["X","O","O","X"],
 * ["X","X","O","X"],
 * ["X","O","X","X"]]
 *
 * 输出：
 * [["X","X","X","X"],
 * ["X","X","X","X"],
 * ["X","X","X","X"],
 * ["X","O","X","X"]]
 *
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SurroundedRegions_130 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        new Solution().solve(board);

        System.out.println(Arrays.deepToString(board));

    }


    /**
     * bfs
     *
     */
    static class Solution {
        public void solve(char[][] board) {
            int row = board.length;
            int col = board[0].length;

            // 找到与边界上的O以及与边界上O相连的O
            Queue<int[]> queue = new LinkedList<>();
            // 遍历第一列和最后一列
            for (int i = 0; i < row; i++) {
                if (board[i][0] == 'O') {
                    queue.offer(new int[]{i, 0});
                    board[i][0] = '#';
                }
                if (board[i][col-1] == 'O') {
                    queue.offer(new int[]{i, col-1});
                    board[i][col-1] = '#';
                }
            }

            for (int i = 0; i < col; i++) {
                if (board[0][i] == 'O') {
                    queue.offer(new int[]{0, i});
                    board[0][i] = '#';
                }
                if (board[row-1][i] == 'O') {
                    queue.offer(new int[]{row - 1, i});
                    board[row-1][i] = '#';
                }
            }

            // bfs更新queue中的边界O，找相连的部分更新
            while (!queue.isEmpty()) {
                int[] position = queue.poll();
                int x = position[0];
                int y = position[1];
                if (x - 1 >= 0 && board[x-1][y] == 'O') {
                    queue.offer(new int[]{x-1, y});
                    board[x-1][y] = '#';
                }
                if (x + 1 < row && board[x+1][y] == 'O') {
                    queue.offer(new int[]{x+1, y});
                    board[x+1][y] = '#';
                }
                if (y - 1 >= 0 && board[x][y-1] == 'O') {
                    queue.offer(new int[]{x, y-1});
                    board[x][y-1] = '#';
                }
                if (y + 1 < col && board[x][y+1] == 'O') {
                    queue.offer(new int[]{x, y+1});
                    board[x][y+1] = '#';
                }

            }


            // 做更新
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }

        }
    }



    /**
     * 这道题目的思路是要将分成三种元素：
     * X
     * 被x包围的O
     * 没有被X包围的O
     *
     * “任何边界上的 O 都不会被填充为 X”，
     * 1.对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母 O；
     * 2. 最后我们遍历这个矩阵，对于每一个字母：
     *  2.1 如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；
     *  2.2 如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，我们将其修改为字母 X。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/surrounded-regions/solution/bei-wei-rao-de-qu-yu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solutiondfs {
        public void solve(char[][] board) {
            int row = board.length - 1;
            int col = board[0].length - 1;

            // 第一行和最后
            for (int j = 0; j <= col; j++) {
                dfs(board, 0, j);
                dfs(board, row, j);
            }

            // 第一列和第最后列
            for (int i = 0; i <= row; i++) {
                dfs(board, i, 0);
                dfs(board, i, col);
            }


            for (int i = 0; i <= row; i++) {
                for (int j = 0; j <= col; j++) {
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(char[][] board, int x, int y) {
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                board[x][y] = '#';
                dfs(board, x - 1, y);
                dfs(board, x + 1, y);
                dfs(board, x, y - 1);
                dfs(board, x, y + 1);
            }

        }
    }

}
