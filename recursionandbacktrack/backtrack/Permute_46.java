package leetcode.recursionandbacktrack.backtrack;


import java.util.LinkedList;
import java.util.List;

// 46 回溯算法 全排列
public class Permute_46 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1, 2, 3};
        solution.permute(nums);
    }



    /**
     * https://labuladong.gitbook.io/algo/mu-lu-ye/hui-su-suan-fa-xiang-jie-xiu-ding-ban
     * 参考文章
     * !!根据文章，调试代码，来画全排列的图就可以了
     *
     * result = []
     * def backtrack(路径, 选择列表):
     *     if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *         # 做选择
     *         将该选择从选择列表移除
     *         路径.add(选择)
     *
     *         backtrack(路径, 选择列表)
     *         # 撤销选择
     *         路径.remove(选择)
     *         将该选择再加入选择列表
     */
    static class Solution {
        List<List<Integer>> result = new LinkedList<>();

        public List<List<Integer>> permute(int[] nums) {
            // 记录路径
            LinkedList<Integer> path = new LinkedList<>();

            backTrack(nums, path);
            return result;
        }

        /**
         * 路径：记录在 track 中
         * 选择列表：nums 中不存在于 track 的那些元素
         * 结束条件：nums 中的元素全都在 track 中出现
         *
         * @param nums
         * @param path 记录路径
         */
        private void backTrack(int[] nums, LinkedList<Integer> path) {
            // // 触发结束条件
            if (path.size() == nums.length) {
                result.add(new LinkedList<>(path));
                return;
            }

            // for 选择 in 选择列表:
            for (int i = 0; i < nums.length; i++) {
                // 这里变了一下
                // 没有显式记录「选择列表」，而是通过 nums 和 track 推导出当前的选择列表

                // 排除不合法的，遇到path中重复的，不要添加，到下一个元素，这里看文章全排列最后的图
                if (path.contains(nums[i])) {
                    continue;
                }

                // # 做选择
                // 将该选择从选择列表移除
                // 路径.add(选择)
                path.add(nums[i]);
                // backtrack(路径, 选择列表)
                backTrack(nums, path);
                // # 撤销选择
                // 路径.remove(选择)
                // 将该选择再加入选择列表
                path.removeLast();
            }
        }
    }



    static class Solution1 {
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
