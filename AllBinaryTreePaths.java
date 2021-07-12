package LeetCode;

import java.util.List;

// 257
public class AllBinaryTreePaths {
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

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode root = new TreeNode(1);
        
        solution.binaryTreePaths(root);
    }


    static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            return null;
        }
    }
}
