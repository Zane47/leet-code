package leetcode.dp;

import java.util.ArrayList;
import java.util.Scanner;

// 213 打家劫舍 II
public class Rob_V2_213 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] strings = input.split(",");

        ArrayList<Integer> list = new ArrayList<>();

        for (String string : strings) {
            list.add(Integer.valueOf(string));
        }

        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        System.out.println(solution.rob(nums));
    }


    // dp
    static class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int len = nums.length;

            return Math.max(rob1(nums, 0, len - 2), rob1(nums, 1, len - 1));

            /*
            int[] dp1 = new int[nums.length];
            dp1[0] = nums[0];
            dp1[1] = Math.max(nums[0], nums[1]);

            int[] dp2 = new int[nums.length];
            dp2[0] = nums[0];
            dp2[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i <= nums.length - 2; i++) {
                dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
            }

            for (int i = 3; i <= nums.length - 1; i++) {
                dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
            }


            return Math.max(dp1[nums.length - 2], dp2[nums.length - 1]);*/
        }

        private int rob1(int[] nums, int start, int end) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = start + 2; i <= end; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[end];
        }
    }

}
