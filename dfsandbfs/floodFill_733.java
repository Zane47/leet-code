package leetcode.dfsandbfs;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 *
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 *
 * 最后返回经过上色渲染后的图像。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
// 类似岛屿问题
public class floodFill_733 {
    public static void main(String[] args) {
        /*int[][] image = new int[][] {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1;
        int sc = 1;
        int newColor = 2;*/
        int[][] image = new int[][] {
            {0, 0, 0},
            {0, 1, 1}
        };
        int sr = 1;
        int sc = 1;
        int newColor = 1;


        System.out.println(Arrays.deepToString(new Solution().floodFill(image, sr, sc, newColor)));
    }

    //bfs
    static class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int rowLength = image.length;
            int colLength = image[0].length;
            int oldColor = image[sr][sc];
            // 避免重复
            boolean[][] visit = new boolean[rowLength][colLength];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{sr, sc});

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int i = current[0];
                int j = current[1];
                if (visit[i][j]) {
                    continue;
                }
                visit[i][j] = true;
                image[i][j] = newColor;
                if (i - 1 >= 0 && image[i - 1][j] == oldColor) {
                    queue.add(new int[]{i - 1, j});
                }
                if (i + 1 < rowLength && image[i + 1][j] == oldColor) {
                    queue.add(new int[]{i + 1, j});

                }
                if (j - 1 >= 0 && image[i][j - 1] == oldColor) {
                    queue.add(new int[]{i, j - 1});

                }
                if (j + 1 < colLength && image[i][j + 1] == oldColor) {
                    queue.add(new int[]{i, j + 1});
                }
            }

            return image;
        }






    }

    // dfs
    static class Solution1 {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int rowLength = image.length;
            int colLength = image[0].length;
            int oldColor = image[sr][sc];
            dfs(image, sr, sc, newColor, rowLength, colLength, oldColor);
            return image;
        }

        private void dfs(int[][] image, int sr, int sc, int newColor,
                         int rowLength, int colLength, int oldColor) {

            if (sr >= 0 && sc >= 0 &&
                sr < rowLength && sc < colLength &&
                image[sr][sc] == oldColor && newColor != oldColor) {
                // 一定要有新旧颜色不一样，不然会超时
                image[sr][sc] = newColor;
                dfs(image, sr - 1, sc, newColor, rowLength, colLength, oldColor);
                dfs(image, sr + 1, sc, newColor, rowLength, colLength, oldColor);
                dfs(image, sr, sc - 1, newColor, rowLength, colLength, oldColor);
                dfs(image, sr, sc + 1, newColor, rowLength, colLength, oldColor);
            }
        }
    }

}
