package leetcode.disjointset;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 注意与岛屿问题求岛屿数量_200的区别
 *              {1,0,0,1},
 *             {0,1,1,0},
 *             {0,1,1,1},
 *             {1,0,1,1}
 *  岛屿就是4，但是此处省份就是1
 *
 *
 *
 * 无向图求连通域数量
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 *
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 *
 *
 *
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfProvinces_547 {

    public static void main(String[] args) {
        int[][] isConnected = new int[][] {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        /*int[][] isConnected = new int[][] {
            {1,0,0,1},
            {0,1,1,0},
            {0,1,1,1},
            {1,0,1,1}
        };*/


        System.out.println(new Solution().findCircleNum(isConnected));

    }


    /**
     * 并查集
     * 图的顶点数为 n，则初始化 n 个单顶点集合，每个集合指向自身。
     *
     * 然后遍历图中的每个顶点，将当前顶点与其邻接点进行合并。最终结果返回合并后的集合的数量即可。
     *
     */
    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            // 初始化并查集
            UnionFind uf = new UnionFind(n);
            // 遍历所有钉钉，将当前顶点与其邻接点进行合并
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1) {
                        uf.union(i, j);
                    }
                }
            }
            // 返回最终合并后的集合的数量
            return uf.size;

        }

        class UnionFind {
            int[] roots;
            int size;

            // 初始化，每一个的根节点是自己
            public UnionFind(int n) {
                size = n;
                roots = new int[n];
                for (int i = 0; i < n; i++) {
                    roots[i] = i;
                }
            }

            // 找到i的根节点
            public int findRoot(int i) {
                if (roots[i] == i) {
                    return i;
                } else {
                    // 递归的方法去找
                    // 注意这里要路径压缩一下 -> https://zhuanlan.zhihu.com/p/93647900/
                    // return findRoot(roots[i]);

                    // 把沿途的每个节点的父节点都设为根节点
                    roots[i] = findRoot(roots[i]);
                    return roots[i];
                }
            }

            // 将两个顶点作为并查集merge
            public void union(int i, int j) {
                // 先找到两个根节点，然后如果不是同一个根的话就做合并
                int rooti = findRoot(i);
                int rootj = findRoot(j);
                if (rooti != rootj) {
                    roots[rooti] = rootj;
                    size--;
                }

            }
        }

    }




    /**
     * bfs
     *
     */
    static class Solutionbfs {

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            boolean[] isVisited = new boolean[n];
            int result = 0;
            // 顶点遍历队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (!isVisited[i]) {
                    result++;
                    queue.offer(i);
                    isVisited[i] = true;
                    while (!queue.isEmpty()) {
                        Integer v = queue.poll();
                        // 到顶点的行
                        for (int j = 0; j < n; j++) {
                            if (isConnected[v][j] == 1 && !isVisited[j]) {
                                queue.offer(j);
                                isVisited[j] = true;
                            }
                        }
                    }
                }
            }


            return result;

        }
    }

    /**
     * dfs
     * isConnected[][]是图的邻接矩阵，n是无向图的顶底数量
     */
    static class Solutiondfs {

        public int findCircleNum(int[][] isConnected) {
            int result = 0;
            // 数组标识顶点是否被访问
            boolean[] isVisited = new boolean[isConnected.length];
            for (int i = 0; i < isConnected.length; i++) {
                // 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且cnt+=1.
                result++;
                dfs(isConnected, i, isVisited);
            }
            return result;
        }

        private void dfs(int[][] isConnected, int i, boolean[] isVisited) {
            // 对当前顶点 i 进行访问标记
            isVisited[i] = true;
            // 继续遍历与顶点 i 相邻的顶点（使用 visited 数组防止重复访问）
            for (int j = 0; j < isConnected.length; j++) {
                dfs(isConnected, j, isVisited);
            }
        }
    }

}





