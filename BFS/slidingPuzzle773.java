package LeetCode.BFS;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

// 773 数字华容道
public class slidingPuzzle773 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = new int[][] {
                {1, 2, 3},
                {4, 0, 5}
        };
        int[][] board1 = new int[][] {
                {1, 2, 3},
                {5, 4, 0}
        };

        int[][] board2 = new int[][] {
                {4, 1, 2},
                {5, 0, 3}
        };
        System.out.println(solution.slidingPuzzle(board));
    }

    static class Solution {
        public int slidingPuzzle(int[][] board) {
            char[] chars = new char[6];
            int index = 0;
            for (int[] row : board) {
                for (int ch : row) {
                    // int转char的方法
                    chars[index] = (char)(ch + '0');
                    index++;
                }
            }

            String start = new String(chars);
            String target = "123450";

            // 0 1 2
            // 3 4 5
            // 对应索引的上下左右的数字有哪些
            int[][] neighbor = new int[][]{
                    {1, 3},
                    {0, 2, 4},
                    {1, 5},
                    {0, 4},
                    {1, 3, 5},
                    {2, 4},
            };

            // BFS
            Queue<String> queue = new ArrayDeque<>();
            HashSet<String> visited = new HashSet<>();
            queue.add(start);
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    // 解开谜板
                    if (cur.equals(target)) {
                        return step;
                    }

                    int position = cur.indexOf('0');
                    int[] exchanges = neighbor[position];

                    for(int next : exchanges) {
                        String s = exchangeString(cur, position, next);
                        if (!visited.contains(s)) {
                            queue.offer(s);
                            visited.add(s);
                        }
                    }
                }
                step++;
            }
            return -1;
        }




        // String交换字符
        public String exchangeString(String string, int src, int dis) {
            char[] chars = string.toCharArray();
            char temp = chars[dis];
            chars[dis] = chars[src];
            chars[src] = temp;
            return new String(chars);
        }

    }
}
