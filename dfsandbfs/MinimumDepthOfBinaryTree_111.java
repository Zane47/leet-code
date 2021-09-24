package leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree_111 {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new Solution().minDepth(root));

    }

    /**
     * bfs层次遍历计算深度
     */
    static class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 这里要初始化1，root本身就是一层
            int depth = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    // 判断是否到了终点
                    if (poll.left == null && poll.right == null) {
                        return depth;
                    }
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                }
                depth++;
            }

            return depth;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
