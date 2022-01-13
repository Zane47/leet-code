package leetcode.dp;

public class ArithmeticSlices_413 {
    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 3, 4, 5, 7};

        int[] nums = new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10};

        System.out.println(new Solution2().numberOfArithmeticSlices(nums));
    }


    /**
     * dp
     */
    static class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int length = nums.length;
            int dp = 0;
            int result = 0;

            for (int i = 2; i < length; i++) {
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    dp++;
                } else {
                    dp = 0;
                }
                // System.out.print(dp + " ");

                result += dp;
            }

            // System.out.println();
            return result;
        }
    }

    static class Solution2 {
        public int numberOfArithmeticSlices(int[] nums) {
            int length = nums.length;
            int result = 0;

            if (length < 3) {
                return 0;
            }

            int[] dp = new int[length];
            dp[0] = 0;
            dp[1] = 0;

            for (int i = 2; i < length; i++) {
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = 0;
                }

                // System.out.print(dp[i] + " ");

                result += dp[i];
            }

            // System.out.println();
            return result;
        }
    }


    /**
     * 直接O(n^2)
     */
    static class Solution1 {
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
