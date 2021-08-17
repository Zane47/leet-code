package leetcode.search.binarysearch;

/**
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class SearchInsertPosition_35 {

    public static void main(String[] args) {

        int[] nums = new int[] {1,3,5,6};
        int target = 2;
        // int target = 7;
        System.out.println(new Solution().searchInsert(nums, target));
    }

    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] > target) {
                    right = mid - 1;
                }
                else if (nums[mid] < target) {
                    left = mid + 1;
                }
                else if (nums[mid] == target) {
                    return mid;
                }
            }

            return left;
        }
    }


}
