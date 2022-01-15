package leetcode.dp;

public class UniqueBinarySearchTrees_96 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(new Solution().numTrees(n));
    }

    /**
     * G(n): 长度为n的序列可以构成的不同BST的个数
     * F(i,n): 以i为根的, 序列长度为n的不同bst的数量
     * <p>
     * G(n)就是对F(i,n), i从[1,n]求和
     * <p>
     * 假设n=7, 求F(3, n)
     * 以3为根节点, [1,2]为左子树, [3,4,5,6,7]为右子树
     * 所以F(3,n) = G(2) * G(4)
     * f(i,n) = G(i-1) * G(n-i) -> i属于[1,n], 求和得到G(n)
     * <p>
     * G(n)只和n有关, 与数字是是什么没有关系
     */
    static class Solution {
        public int numTrees(int n) {
            int[] G = new int[n + 1];

            // base case:
            G[0] = 1;
            G[1] = 1;

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    G[i] += G[j - 1] * G[i - j];
                }
            }

            return G[n];
        }
    }
}
