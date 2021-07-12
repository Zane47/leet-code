package LeetCode.dp;

import javafx.scene.SnapshotParameters;

import java.util.ArrayList;
import java.util.Scanner;

public class Rob_198 {
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

    /**
     * dp[i]表示到第i个房子偷到的价值之和
     * 对于每一个房屋都可以偷or不偷，第i个房屋，如果偷，那么dp[i]就是i的价值加上之前i-2
     * 如果不偷，dp[i]就是之前i-1房子的偷盗的钱之和
     *
     * dp[i] 表示前 i 间房屋能偷窃到的最高总金额
     * dp[i] = max(dp[i-2] + nums, dp[i-1])
     *
     * 边界为dp[0] = 0, dp[1] = max(dp[0], dp[1])
     */
    static class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            if (nums.length == 1) {
                return nums[0];
            }

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2;  i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            return dp[nums.length-1];
        }




    }



}
