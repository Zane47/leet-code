package leetcode.dp.bag;

import java.util.Arrays;

/**
 * 数组分成两个子集, 子集元素和相等
 * https://labuladong.gitee.io/algo/3/25/81/
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * <p>
 * 一开始感觉不是背包, 但是转换题目可以得到 ->
 * 先对集合求和，得出 sum，把问题转化为背包问题：
 * 给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。
 * 现在让你装物品，是否存在一种装法，能够恰好将背包装满？
 */
public class PartitionEqualSubsetSum_416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(new Solution().canPartition(nums));
    }

    /**
     * 二维dp
     * dp[i][v] = x, 对于前i个物品, 背包容量为v的时候, x==true: 恰好可以把背包装满, 反之则反
     * <p>
     * 比如说，如果 dp[4][9] = true:
     * 对于容量为 9 的背包，若只是用前 4 个物品，可以有一种方法把背包恰好装满。
     * 换一种说法, 对给定的子集中, 只用前四个数就可以得到子集和为9
     * <p>
     * 状态转移:
     * 如果不把 nums[i] 算入子集，或者说你不把这第 i 个物品装入背包，
     * 那么是否能够恰好装满背包，取决于上一个状态 dp[i-1][j]，继承之前的结果。
     * <p>
     * 如果把 nums[i] 算入子集，或者说你把这第 i 个物品装入了背包，
     * 那么是否能够恰好装满背包，取决于状态 dp[i-1][j-nums[i-1]]。
     * <p>
     * 首先，由于 i 是从 1 开始的，而数组索引是从 0 开始的，所以第 i 个物品的重量应该是 nums[i-1]，这一点不要搞混。
     * <p>
     * dp[i - 1][j-nums[i-1]] 也很好理解：你如果装了第 i 个物品，就要看背包的剩余重量 j - nums[i-1] 限制下是否能够被恰好装满。
     * <p>
     * 换句话说，如果 j - nums[i-1] 的重量可以被恰好装满，那么只要把第 i 个物品装进去，也可恰好装满 j 的重量；否则的话，重量 j 肯定是装不满的。
     */
    static class Solution {
        public boolean canPartition(int[] nums) {
            int n = nums.length;

            int sum = Arrays.stream(nums).sum();
            // sum为奇数的时候, 不可能分成两个等和的子集
            if (sum % 2 == 1) {
                return false;
            }

            // 对于前i个物品, 背包容量为v的时候, 是否可以把背包装满
            int V = sum / 2;
            boolean[][] dp = new boolean[n + 1][V + 1];

            // 初始化base case
            // dp[..][0] = true: 背包没有空间的时候，就相当于装满了
            // dp[0][..] = false，当没有物品可选择的时候，肯定没办法装满背包。
            Arrays.fill(dp[0], false);
            for (int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= n; i++) {
                for (int v = 1; v <= V; v++) {
                    // 重量大于v, 放不下
                    if (nums[i - 1] > v) {
                        dp[i][v] = dp[i - 1][v];
                    } else {
                        // 可以放下, 看要不要放下
                        // 1. 前i-1个数可以组成v, 那么要不要第i个数都可以
                        // 2. 前i-1个数不可以组成v,
                        // 那么看前i-1个数能不能组成v-nums[i]
                        // 否则就要看看在前i-1个数是否可以凑出v-nums[i]的和
                        // dp[i][v] =  dp[i-1][v] or dp[i-1][v-nums[i]]


                        // 要么前i-1个数中已经凑出了v的和
                        // 要么前i-1个数凑出了v-nums[i]的和, 那么这次直接加上nums[i]

                        dp[i][v] = dp[i - 1][v] || dp[i - 1][v - nums[i - 1]];
                    }
                }
            }

            return dp[n][V];
        }
    }


}
