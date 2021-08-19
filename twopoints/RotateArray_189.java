package leetcode.twopoints;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
     * 首先对整个数组实行翻转，这样子原数组中需要翻转的子数组，就会跑到数组最前面。
     * 这时候，从 k 处分隔数组，左右两数组，各自进行翻转即可。
     *
     *
     * 1, 2, 3, 4, 5, 6, 7   k = 3
     * 1.把整个数组翻转，7654321
     * 2.找到k=3的分解处，765  4321
     * 3.各自翻转 567 1234
     *
     */
    static class Solution {
        public void rotate(int[] nums, int k) {
            // k可能超过数组长度
            k = k % nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        /**
         * 数组翻转
         * @param nums nums
         * @param left start
         * @param right end
         */
        private void reverse(int[] nums, int left, int right) {
            while (left < right) {
                // left <= right也可以
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
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
