package leetcode.dfsandbfs;

import com.sun.media.sound.RIFFInvalidDataException;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
 * 那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class MergeTwoBinaryTrees_617 {
    public static void main(String[] args) {



    }


    /**wrong
     * 根节点开始遍历
     *
     *
     *
     * 如果合并后的二叉树的左子节点不为空，
     * 则需要根据两个原始二叉树的左子节点计算合并后的二叉树的左子节点以及整个左子树。
     * 考虑以下两种情况：
     * 1.如果两个原始二叉树的左子节点都不为空，
     * 则合并后的二叉树的左子节点的值为两个原始二叉树的左子节点的值之和，
     * 在创建合并后的二叉树的左子节点之后，
     * 将每个二叉树中的左子节点都加入相应的队列；
     *
     * 2.如果两个原始二叉树的左子节点有一个为空，
     * 即有一个原始二叉树的左子树为空，
     * 则合并后的二叉树的左子树即为另一个原始二叉树的左子树，
     * 此时也不需要对非空左子树继续遍历，
     * 因此不需要将左子节点加入队列。
     *
     * 对于右子节点和右子树，处理方法与左子节点和左子树相同。
     */
    static class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }

            TreeNode result = new TreeNode(root1.val + root2.val);
            Queue<TreeNode> queue = new LinkedList<>();
            Queue<TreeNode> queue1 = new LinkedList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();

            queue.offer(result);
            queue1.offer(root1);
            queue2.offer(root2);

            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();
                TreeNode left = node.left, right = node.right,
                    left1 = node1.left, right1 = node1.right,
                    left2 = node2.left, right2 = node2.right;

                if (left1 != null || left2 != null) {
                    if (left1 != null && left2 != null) {
                        TreeNode treeNode = new TreeNode(left1.val + left2.val);
                        result.left = treeNode;

                        queue.offer(treeNode);
                        queue1.offer(left1);
                        queue2.offer(left2);
                    } else if (left1 != null) {
                        // Left2 is null
                        result.left = new TreeNode(left1.val);
                    } else if (left2 != null) {
                        // left1 is null
                        result.left = new TreeNode(left2.val);
                    }
                }

                if (right1 != null || right2 != null) {
                    if (right1 != null && right2 != null) {
                        TreeNode treeNode = new TreeNode(right1.val + right2.val);
                        result.right = treeNode;

                        queue.offer(right);
                        queue1.offer(right1);
                        queue2.offer(right2);
                    } else if (right1 != null) {
                        result.right = new TreeNode(right1.val);
                    } else if (right2 != null) {
                        result.right = new TreeNode(right2.val);
                    }
                }
            }
            return result;
        }
    }

    /**
     * dfs
     * 同时dfs
     *
     */
    static class Solution1 {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }
            TreeNode result = new TreeNode(root1.val + root2.val);
            result.left = mergeTrees(root1.left, root2.left);
            result.right = mergeTrees(root1.right, root2.right);
            return result;
        }
    }

}


class TreeNode {
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