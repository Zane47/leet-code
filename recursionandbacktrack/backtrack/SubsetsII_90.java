package leetcode.recursionandbacktrack.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * !!!解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class SubsetsII_90 {
    public static void main(String[] args) {

        int[] nums = new int[]{1,2,2};

        // 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println(new Solution().subsetsWithDup(nums));
    }

    /**
     *
     * 重点是
     * 解集不能包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * 去重，其实就是使用过的元素不能重复选取
     */
    static class Solution {
        private List<List<Integer>> result = new ArrayList<>();
        private List<Integer> tempPath = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // 去重要先排序
            Arrays.sort(nums);
            // used数组记录哪一个元素在当前路径中被使用了
            boolean[] isUsed = new boolean[nums.length];
            backTrack(nums, 0, isUsed);
            return result;
        }

        private void backTrack(int[] nums, int start, boolean[] isUsed) {
            result.add(new ArrayList<>(tempPath));
            for (int i = start; i < nums.length; i++) {
                // 避免重复添加
                // 如果当前元素和前一个元素相同，而且前一个元素没有被访问，
                // 说明前一个相同的元素在当前层已经被用过了
                if (i > 0 && nums[i] == nums[i-1] && !isUsed[i-1]) {
                    continue;
                }
                // 加入
                tempPath.add(nums[i]);
                isUsed[i] = true;
                // 回溯
                backTrack(nums, i + 1, isUsed);
                // 退出
                tempPath.remove(tempPath.size() - 1);
                isUsed[i] = false;
            }
        }
    }



}
