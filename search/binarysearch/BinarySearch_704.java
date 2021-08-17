package leetcode.search.binarysearch;

public class BinarySearch_704 {

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 3, 5, 9, 12};

        int target = 9;
        // int target = 2;

        System.out.println(new Solution().search(nums, target));
    }

    static class Solution {
        public int search(int[] nums, int target) {
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
            return -1;
        }
    }


}
