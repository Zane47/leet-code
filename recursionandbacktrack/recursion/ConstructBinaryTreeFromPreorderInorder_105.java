package leetcode.recursionandbacktrack.recursion;

import java.util.Arrays;

/**
 * 已知preOrder和inOrder, 建立树
 * preOrder可以告诉我们根节点的位置
 * inOrder可以用来区分左右子树
 */
public class ConstructBinaryTreeFromPreorderInorder_105 {
    public static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};

        new Solution().buildTree(preOrder, inOrder);
    }

    /**
     * 前序遍历：根左右，前序遍历的第一个元素就是根节点
     * 中序遍历：左根右，中序遍历可以根据根节点分成左子树和右子树
     * e.g.:
     * preOrder: 1 2 4 7 3 5 6 8
     * inOrder: 4 7 2 1 5 3 8 6
     * 从preOrder中可以看出根节点是1，那么inorder中按照根节点坐在的位置可以知道左右子树的序列
     * inOrder: 左子树： 4 7 2； 根节点： 1； 右子树：5 3 8 6
     * 同样看左子树序列，也可以通过preOrder来计算该左子树的根节点是2，然后再计算左子树的左子树和右子树
     * 递归构建
     * <p>
     * 1.preorder第一个元素就是root
     * 2.计算root在inorder中的index，划分左子树和右子树
     * 3.递归调用，左子树的preorder和右子树的preorder
     * 这里写一个数组切片函数即可，按照习惯，左闭右开
     */
    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0 || inorder.length == 0) {
                return null;
            }

            int tempRoot = preorder[0];
            int indexOfTempRootInorder = 0;
            for (; indexOfTempRootInorder < inorder.length; indexOfTempRootInorder++) {
                if (inorder[indexOfTempRootInorder] == tempRoot) {
                    break;
                }
            }
            TreeNode treeNode = new TreeNode(tempRoot);

            treeNode.left = buildTree(
                    slice(preorder, 1, indexOfTempRootInorder + 1),
                    slice(inorder, 0, indexOfTempRootInorder));

            treeNode.right = buildTree(
                    slice(preorder, indexOfTempRootInorder + 1, preorder.length),
                    slice(inorder, indexOfTempRootInorder + 1, inorder.length));

            return treeNode;
        }

        /**
         * 数组切片, 左闭右开
         */
        private int[] slice(int[] array, int left, int right) {
            return Arrays.copyOfRange(array, left, right);
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }


}
