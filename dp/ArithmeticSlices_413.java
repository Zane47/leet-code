package leetcode.dp;

public class ArithmeticSlices_413 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(new Solution().numberOfArithmeticSlices(nums));
    }



    /**
     * 直接O(n^2)
     */
    static class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int length = nums.length;
            if (length < 3) {
                return 0;
            }
            int result = 0;
            for (int i = 0; i <= length - 3; i++) {
                int diff = nums[i + 1] - nums[i];
                for (int j = i + 2; j <= length - 1; j++) {
                    if (nums[j] - nums[j - 1] == diff) {
                        result++;
                    } else {
                        break;
                    }
                }
            }
            return result;
        }
    }
}
