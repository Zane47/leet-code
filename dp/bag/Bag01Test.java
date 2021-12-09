package leetcode.dp.bag;

/**
 * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.md
 *
 * dp[i][j]的含义：
 * 从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
 *
 * !!! 这里的dp和下文的dp概念不一样
 *
 * 那么可以有两个方向推出来dp[i][v]
 *
 * 1. 不放物品i：由dp[i - 1][v]推出，即背包容量为v，里面不放物品i的最大价值，
 * 此时dp[i][j]就是dp[i - 1][v]。
 * (其实就是当物品i的重量大于背包v的重量时, 物品i无法放进背包中, 所以被背包内的价值依然和前面相同。)
 *
 *
 * 2. 放物品i：由dp[i - 1][v - weight[i]]推出，
 * dp[i - 1][v - weight[i]] 为背包容量为j - weight[i]的时候不放物品i的最大价值，
 * 那么dp[i - 1][v - weight[i]] + value[i](物品i的价值), 就是背包放物品i得到的最大价值
 *
 *
 *
 */
public class Bag01Test {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem(weight.length, weight, value, bagSize);
    }

    public static void testWeightBagProblem(int n, int[] weight, int[] value, int bagSize) {
        // dp[i][v]: dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        // 前i件物品(1<=i<=n, 0<=v<=V)恰好装入容量为v的背包中所能获得的最大价值
        int[][] dp = new int[n + 1][bagSize + 1];

        // 初始化：背包容量为0时，能获得的价值都为0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 遍历顺序：先遍历物品，再遍历背包容量
        // 遍历物品, n为物品个数
        for (int i = 1; i <= n; i++) {
            // 遍历背包容量
            for (int v = 1; v <= bagSize; v++) {
                // 其实就是当物品i的重量大于背包v的重量时,
                // 物品i无法放进背包中, 所以被背包内的价值依然和前面相同
                if (weight[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    // 物品i可以放入背包,重量小于背包容量, 看要不要放入
                    dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //打印dp数组
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}
