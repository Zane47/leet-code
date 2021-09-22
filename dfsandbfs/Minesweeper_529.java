package leetcode.dfsandbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 *
 * 让我们一起来玩扫雷游戏！
 *
 * 给定一个代表游戏板的二维字符矩阵。 
 * 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，
 * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 *
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），
 * 根据以下规则，返回相应位置被点击后对应的面板：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *
 *
 * 示例 1：
 *
 * 输入:
 *
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 *
 * Click : [3,0]
 *
 * 输出:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 *
 *
 * 示例 2：
 *
 * 输入:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Click : [1,2]
 *
 * 输出:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */

public class Minesweeper_529 {


    public static void main(String[] args) {

        char[][] board = new char[][] {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click = new int[]{3, 0};

        System.out.println(Arrays.deepToString(new Solution().updateBoard(board, click)));
    }


    /**
     * 模拟+bfs
     */
    static class Solution {
        int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = new int[]{0, 0, -1, 1, -1, 1, -1, 1};

        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0];
            int y = click[1];

            // 规则1：如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return board;
            }

            // 点击了E
            // bfs
            boolean[][] isVisited = new boolean[board.length][board[0].length];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            isVisited[x][y] = true;
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int i = point[0];
                int j = point[1];
                int count = 0;

                // 判断周围是否有雷
                for (int v1 = 0; v1 < 8; v1++) {
                    int newX = i + dx[v1];
                    int newY = j + dy[v1];
                    if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                            && board[newX][newY] == 'M') {
                        count++;
                    }
                }
                // 若空地 (i, j) 周围有雷，则将该位置修改为雷数；
                // 否则将该位置更新为 ‘B’，并将其 8 邻域中的空地入队，继续进行 bfs 搜索。
                if (count > 0) {
                    // (i, j) 周围有雷
                    board[i][j] = (char) ('0' + count);
                } else {
                    // (i, j) 周围没雷
                    board[i][j] = 'B';
                    // 将剩下的8个相邻的空地加入queue
                    for (int v1 = 0; v1 < 8; v1++) {
                        int newX = i + dx[v1];
                        int newY = j + dy[v1];
                        if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                                && board[newX][newY] == 'E' && !isVisited[newX][newY]) {
                            // 注意不要重复入queue
                            isVisited[newX][newY] = true;
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            return board;
        }
    }


    /**
     * 模拟+dfs
     *
     *
     * 总共分两种情况:
     * 1.当前点击的是「未挖出的地雷」，我们将其值改为 X 即可。
     *
     * 2.当前点击的是「未挖出的空方块」，
     * 我们需要统计它周围相邻的方块里地雷的数量 cnt（即 M 的数量）。
     * 如果 cnt 为零，即执行规则 2，此时需要将其改为 B，且递归地处理周围的八个未挖出的方块，
     * 递归终止条件即为规则 4，没有更多方块可被揭露的时候。
     *
     * 否则执行规则 3，将其修改为数字即可。
     * ---------------------------------------------------------
     * 若起点处是雷，即 ‘M’，直接将其修改为 'X'，游戏结束；
     * 若起点处是空，即 ‘E’，则从起点开始向 8 邻域中的空地搜索，直到到达邻接雷的空地停止。
     * 和二叉树从根结点开始搜索，直到达到叶子节点停止，是几乎一样的，
     * 所以会写二叉树的 BFS/DFS，那么这题也就写出来了。
     *
     * 作者：sweetiee
     * 链接：https://leetcode-cn.com/problems/minesweeper/solution/cong-qi-dian-kai-shi-dfs-bfs-bian-li-yi-bian-ji-ke/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minesweeper/solution/sao-lei-you-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solutiondfs {
        int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = new int[]{0, 0, -1, 1, -1, 1, -1, 1};

        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0];
            int y = click[1];

            // 规则1：如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
            } else {
                // 点击了E ->
                dfs(board, x, y);
            }
            return board;
        }

        private void dfs(char[][] board, int i, int j) {
            // 雷的个数
            int count = 0;
            // 递归终止条件：
            // 判断空地 (i, j) 周围是否有雷，若有，则将该位置修改为雷数，终止该路径的搜索。
            for (int v1 = 0; v1 < 8; v1++) {
                int x = i + dx[v1];
                int y = j + dy[v1];
                // 越界则到下一个位置
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'M') {
                    count++;
                }
            }
            if (count > 0) {
                board[i][j] = (char) ('0' + count);
                return;
            }

            // 如果旁边没有雷，则将该位置修改为 ‘B，去其他的相邻空地进行搜索
            board[i][j] = 'B';
            for (int v1 = 0; v1 < 8; v1++) {
                int x = i + dx[v1];
                int y = j + dy[v1];
                if (x >=0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'E') {
                    dfs(board, x, y);
                }
            }


        }
    }

}
