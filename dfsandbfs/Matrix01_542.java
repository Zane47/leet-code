package leetcode.dfsandbfs;

import java.util.Arrays;
import java.util.Scanner;

/**  -> 1162地图分析
 * 给定一个由 0 和 1 组成的矩阵 mat，
 * 请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 *
 */
public class Matrix01_542 {

    public static void main(String[] args) {
        int[][] mat = new int[][]{
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };

        System.out.println(Arrays.deepToString(new Solution().updateMatrix(mat)));
    }

    /**
     * 注意是曼哈顿距离
     */
    static class Solution {
        public int[][] updateMatrix(int[][] mat) {
            int rowLength = mat.length;
            int colLength = mat[0].length;




            return mat;
        }
    }

}
