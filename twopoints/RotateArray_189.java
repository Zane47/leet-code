package leetcode.twopoints;

import java.util.Arrays;

/**
 *
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 */
public class RotateArray_189 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new Solution().rotate(nums, k);
        System.out.println(Arrays.toString(nums));


    }

    /**
     *
     */
    static class Solution {
        public void rotate(int[] nums, int k) {

        }
    }





    /**
     * 新增一个数组空间
     * (index + k) % length
     */
    static class Solution1 {
        public void rotate(int[] nums, int k) {
            int length = nums.length;
            int[] result = new int[length];
            for (int i = 0; i < length; i++) {
                result[(i + k) % length] = nums[i];
            }

            System.arraycopy(result, 0, nums, 0, length);
        }



    }



}
