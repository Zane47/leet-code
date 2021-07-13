package leetcode;

import java.util.LinkedList;
import java.util.List;

// 46 回溯算法 全排列
public class Permute_46 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1, 2, 3};
        solution.permute(nums);
    }




    static class Solution {
        List<List<Integer>> result = new LinkedList<>();

        public List<List<Integer>> permute(int[] nums) {
            LinkedList<Integer> trackList = new LinkedList<>();
            int[] visited = new int[nums.length];

            backTrace(nums, trackList);
            //backTrace1(nums, trackList, visited);
            return result;
        }

        private void backTrace1(int[] nums, LinkedList<Integer> trackList, int[] visited) {
            if (trackList.size() == nums.length) {
                result.add(new LinkedList(trackList));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i] == 1) {
                    continue;
                }
                visited[i] = 1;
                trackList.add(nums[i]);

                backTrace1(nums, trackList, visited);
                visited[i] = 0;
                trackList.removeLast();
            }





        }

        // 路径： 记录在 track 中
        // 选择列表： nums 中不存在于 track 的那些元素
        // 结束条件： nums 中的元素全都在 track 中出现
        private void backTrace(int[] nums, LinkedList<Integer> trackList) {
            // 触发结束条件
            if (trackList.size() == nums.length) {
                result.add(new LinkedList(trackList));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 排除不合法的选择
                if (trackList.contains(nums[i])) {
                    continue;
                }
                // 做选择
                trackList.add(nums[i]);
                // 进⼊下⼀层决策树
                backTrace(nums, trackList);
                // 取消选择
                trackList.removeLast();
            }



        }

    }


}
