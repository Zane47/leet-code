package leetcode.recursionandbacktrack.backtrack;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class Permutations2_47 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};

        System.out.println(new Solution().permuteUnique(nums));
    }

    /**
     * 46的解法中，用contains来判断是否是重复添加的，但是这里原本就有重复的所以要更换方法
     * 所以可以用一个visited数组来判断是否访问过
     *
     * 按照全排列的方法来画出树，会有重复
     * hashset也无法比对列表
     *
     */
    static class Solution {
        List<List<Integer>> result = new LinkedList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {

            boolean[] isVisited = new boolean[nums.length];
            LinkedList<Integer> path = new LinkedList<>();

            backTrack(nums, isVisited);

            return result;
        }

        private void backTrack(int[] nums, boolean[] isVisited) {



        }

    }

}




