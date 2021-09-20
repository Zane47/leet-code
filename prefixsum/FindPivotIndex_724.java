package leetcode.prefixsum;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 *
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，
 * 因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 *
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 *
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心下标。
 *
 *
 * 示例 3：
 * 输入：nums = [2, 1, -1]
 * 输出：0
 * 解释：
 * 中心下标是 0 。
 * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。

 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class FindPivotIndex_724 {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        int[] nums = new int[]{2, 1, -1};
        System.out.println(new Solution().pivotIndex(nums));

    }

    /**
     * 前缀和
     * 所有元素的和为total，遍历到i的时候，左侧元素之和为sum，右侧元素之和为total - sum - num[i]
     * 左右相等：sum = total - sum - num[i]，也就是：2sum + nums[i] = total
     */
    static class Solution {
        public int pivotIndex(int[] nums) {
            int total = Arrays.stream(nums).sum();
            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                // leftSum + nums[i] + rightSum = total
                // 其中leftSum == rightSum
                if (2 * leftSum + nums[i] == total) {
                    return i;
                } else {
                    leftSum += nums[i];
                }

            }
            return -1;

        }
    }


    /**
     * 模拟，暴力
     *
     */
    static class Solution1 {
        public int pivotIndex(int[] nums) {
            int tempPivot = 0;
            int length = nums.length;
            while (tempPivot < length) {
                // 1, 7, 3, 6(*0), 5, 6
                if (calcSum(nums, 0, tempPivot) == calcSum(nums, tempPivot + 1, nums.length)) {
                    return tempPivot;
                } else {
                    tempPivot++;
                }
            }

            return -1;
        }

        private int calcSum(int[] nums, int i, int j) {
            // 计算从i到j的和，前闭后开
            int result = 0;
            // 前闭后开
            int[] newArray = Arrays.copyOfRange(nums, i, j);
            for (int v1 : newArray) {
                result += v1;
            }
            return result;

        }
    }



}
