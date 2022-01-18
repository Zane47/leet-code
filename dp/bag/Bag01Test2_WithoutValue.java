package leetcode.dp.bag;


/**不带价值
 * 最多可以装多满
 * dp[i][j]: 前i个数中挑出若干个数总和<=j的最大和是多少
 */
public class Bag01Test2_WithoutValue {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int bagSize = 11;
        System.out.println(testWeightBagProblem(weight, bagSize));
    }

    public static int testWeightBagProblem(int[] weight, int bagSize) {
        int n = weight.length;

        if (weight == null || n == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][bagSize + 1];

        // ------------------------ handle ------------------------
        // 遍历, 先遍历物品, 再遍历bagSize
        // 下面是i-1, 所以i从1开始
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= bagSize; j++) {
                // 第i个物品的重量大于要凑的重量和j, 那么就不放入第i个物品 -> dp[i][j] = dp[i-1][j]
                // 第i个物品下标是i-1
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 第i个物品可以放下, 放与不放, 看放了之后是否可以凑得更大:
                    // 不放的话, 就是dp[i-1][j]
                    // 放的话, 就是前i个物品凑j-A[i], 第i个物品凑A[i]
                    // 看这两个情况哪个大
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i - 1][j - weight[i - 1]] + weight[i - 1]);
                }
            }
        }

        // 最多可以装多满
        return dp[n][bagSize];
    }

}
