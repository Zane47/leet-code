package leetcode.twopoints;

import java.util.Arrays;


/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */

public class MoveZeroes_283 {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }


    static class Solution {
        public void moveZeroes(int[] nums) {





        }
    }


}
