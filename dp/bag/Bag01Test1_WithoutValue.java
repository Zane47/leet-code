package leetcode.dp.bag;

/**不带价值
 * 最多可以装多满
 * -> 能否凑成bagSize, boolean, 然后找可以凑成的最大v
 *
 */
public class Bag01Test1_WithoutValue {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        // int[] value = {15, 20, 30};
        int bagSize = 11;
        System.out.println(testWeightBagProblem(weight, bagSize));
    }

    public static int testWeightBagProblem(int[] weight, int bagSize) {
        int n = weight.length;

        boolean[][] dp = new boolean[n + 1][bagSize + 1];

        // ------------------------ initial ------------------------
        // 前0个数字凑数字0, 为true. 其他的初始化为false
        dp[0][0] = true;


        // ------------------------ handle ------------------------
        // 先遍历数字再遍历容量
        // 因为有i-1, 从i==1开始遍历
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= bagSize; j++) {
                // 第i个物品的重量大于要凑的重量和j, 那么就不放入第i个物品 -> dp[i][j] = dp[i-1][j]
                // 务必注意: 第i个物品下标是i-1
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // dp[i][j]: 前i个物品挑若干个能否凑成数字j
                    // 第i个物品凑不凑的成有两种情况:
                    // 要么是前i-1个物品已经凑成了, 不需要第i个物品
                    // 要么是前i-1个物品凑成了j-weight[i-1], 再加上第i个物品的weight[i-1]正好凑成
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - weight[i - 1]];
                }
            }
        }

        // ------------------------ output ------------------------
        for (int v = bagSize; v >= 0; v--) {
            if (dp[n][v]) {
                return v;
            }
        }
        return -1;
    }

}
