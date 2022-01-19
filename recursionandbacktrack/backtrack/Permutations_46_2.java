package leetcode.recursionandbacktrack.backtrack;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 * 你可以 按任意顺序 返回答案。
 */
public class Permutations_46_2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Solution().permute(nums));
    }


    /**
     * visited数据查看是否被选择
     */
    static class Solution {


        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> tempPath = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];

            backTrack(result, tempPath, visited, nums);

            return result;
        }

        private void backTrack(List<List<Integer>> result,
                               List<Integer> tempPath,
                               boolean[] visited, int[] nums) {
            // 终止条件
            if (tempPath.size() == nums.length) {
                result.add(new ArrayList<>(tempPath));
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                tempPath.add(nums[i]);
                backTrack(result, tempPath, visited, nums);
                visited[i] = false;
                tempPath.remove(tempPath.size() - 1);
            }


        }
    }


    /**
     * https://labuladong.gitee.io/algo/1/5/
     * contains的方法查看是否被选择
     */
    static class Solution1 {

        List<List<Integer>> result = new ArrayList<>();

        LinkedList<Integer> tempPath = new LinkedList<>();

        public List<List<Integer>> permute(int[] nums) {
            if (tempPath.size() == nums.length) {
                result.add(new LinkedList<>(tempPath));
                return result;
            }

            for (int i = 0; i < nums.length; i++) {
                // 应为对链表使用 contains 方法需要 O(N) 的时间复杂度。
                // 有更好的方法通过交换元素达到目的
                if (tempPath.contains(nums[i])) {
                    continue;
                }
                tempPath.add(nums[i]);
                permute(nums);
                tempPath.removeLast();
            }

            return result;
        }
    }


}
