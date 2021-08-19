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

    /**Solution4
     * 一次遍历
     *
     *
     */
    static class Solution {
        public void moveZeroes(int[] nums) {
            int v1 = 0;
            int v2 = 0;

            while (v2 < nums.length) {
                if (nums[v2] != 0) {
                    swap(nums, v1, v2);
                    v1++;
                }
                v2++;
            }
        }
        private void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }

    /**Solution3,其实和2一样，只是不交换，直接覆盖
     * 两次遍历
     * 第一次
     * 0 1 0 3 12
     * 1 1 0 3 12
     * 1 3 0 3 12
     * 1 3 12 3 12
     * 第二次
     * 1 3 12 0 0
     * https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
     */
    static class Solution3 {
        public void moveZeroes(int[] nums) {
            int v1 = 0;
            int v2 = 0;
            while (v2 < nums.length) {
                if (nums[v2] != 0) {
                    nums[v1] = nums[v2];
                    v1++;
                }
                v2++;
            }
            for (int i = v1; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }



    /**Solution2
     * 双指针移动0
     * v1指向已经处理好的序列的尾部，v2指向未处理序列的头部
     * v2向右移动，每次遇到非零的数字就把v1v2互换，然后右移v1
     *
     * 注意到以下性质：
     *
     * 左指针左边均为非零数；
     *
     * 右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution2 {
        public void moveZeroes(int[] nums) {
            int v1 = 0;
            int v2 = 0;
            while (v2 < nums.length) {
                if (nums[v2] != 0) {
                    swap(nums, v1, v2);
                    v1++;
                }
                v2++;
            }
        }

        private void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }

    }





    /**
     * 不移动，删除0，在最后补充0
     */
    static class Solution1 {
        public void moveZeroes(int[] nums) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[index] = nums[i];
                    index++;
                }
            }

            for (int i = index; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }


}
