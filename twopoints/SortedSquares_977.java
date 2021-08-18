package leetcode.twopoints;

import java.util.Arrays;

/**
 * 给你一个按非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 */
public class SortedSquares_977 {


    public static void main(String[] args) {
        int[] nums = new int[] {-4, -1, 0, 3, 10};

        System.out.println(Arrays.toString(new Solution().sortedSquares(nums)));
    }

    /**
     * two points
     * 利用数组特征：非递减
     * 如果全都是正数，那么平方后的循序不变
     * 如果全都是负数，那么平方后的循序倒叙
     * 找到分界线，那么平方后每个部分都是有序(正序or倒序)的
     * 再使用归并排序
     */
    static class Solution {
        public int[] sortedSquares(int[] nums) {




            return null;
        }
    }

    /**
     * 直接法
     */
    static class Solution1 {
        public int[] sortedSquares(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) Math.pow(nums[i], 2);
            }
            Arrays.sort(nums);
            return nums;
        }
    }


}
