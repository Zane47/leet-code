package leetcode.recursionandbacktrack.backtrack;


import java.util.*;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class Permutations2_47 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};

        System.out.println(new Solution().permuteUnique(nums));
    }

    /**
     * https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
     * 46的解法中，用contains来判断是否是重复添加的，但是这里原本就有重复的所以要更换方法
     * 所以可以用一个visited数组来判断是否访问过
     * <p>
     * 按照全排列的方法来画出树，会有重复
     * hashset也无法比对列表
     * <p>
     * 预先对数组排序, 然后查看这个数字是否和前一个数字一致, 如果一致的话就不做处理
     */
    static class Solution {
        List<List<Integer>> result = new LinkedList<>();

        LinkedList<Integer> tempPath = new LinkedList<>();


        public List<List<Integer>> permuteUnique(int[] nums) {

            boolean[] visited = new boolean[nums.length];

            // 使用 Deque 是 Java 官方 Stack 类的建议
            // Deque<Integer> path = new ArrayDeque<>(len);

            Arrays.sort(nums);

            backTrack(nums, visited);

            return result;
        }

        private void backTrack(int[] nums, boolean[] visited) {
            if (tempPath.size() == nums.length) {
                result.add(new ArrayList<>(tempPath));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
                // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
                if (i > 0 && nums[i - 1] == nums[i] && !visited[i-1]) {
                    continue;
                }
                visited[i] = true;
                tempPath.add(nums[i]);
                backTrack(nums, visited);
                visited[i] = false;
                tempPath.remove(tempPath.size() - 1);

            }


        }

    }

}




