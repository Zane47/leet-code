package leetcode.recursionandbacktrack.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 从数组中取k个数的全部组合(不去重)
 * C 6 2 = 6*5 / 2*1
 *
 */
public class Combinations_My {
    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        int[] nums = new int[]{1, 2, 2, 4, 5, 6};
        int k = 2;
        System.out.println(new Solution().conbine(nums, k));
    }

    static class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempPath = new ArrayList<>();

        public List<List<Integer>> conbine(int[] nums, int k) {
            backTrack(nums, k, 0);
            System.out.println(result.size());
            return result;
        }

        private void backTrack(int[] nums, int k, int start) {
            if (tempPath.size() == k) {
                result.add(new ArrayList<>(tempPath));
                return;
            }
            for (int i = start; i < nums.length; i++) {
                tempPath.add(nums[i]);
                System.out.println(tempPath);
                backTrack(nums, k, i + 1);
                tempPath.remove(tempPath.size() - 1);
                System.out.println(tempPath);
            }
        }
    }
}
