package leetcode.dp.bag;


import java.util.Arrays;

/**
 * 自己的写法
 * 时间和空间复杂度O(nV)
 */
public class Bag01Test_2 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem2(weight.length, weight, value, bagSize);
    }

    private static void testWeightBagProblem2(int n, int[] weight, int[] value, int bagSize) {

        // dp[i][v]: 前i个物品恰好组成容量为v的最大价值
        int dp[][] = new int[n + 1][bagSize + 1];

        // dp[0][]初始化, 选取0个物品的时候, 价值为0
        Arrays.fill(dp[0], 0);

        // dp[][0]初始化, 容量为0的时候, 价值为0
        for (int v = 0; v < bagSize; v++) {
            dp[v][0] = 0;
        }

        // 遍历
        // 前i个, [0, i-1], 第i-1个, 所以w和value取第i-1个
        // 双for范围都是[1, n/V], 初始化过来, 第0行和第0列都初始化成0了
        // 从第1行和第1列开始遍历
        for (int i = 1; i <= n; i++) {
            for (int v = 1; v <= bagSize; v++) {
                // 重量大于v, 放不下
                if (weight[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    // 可以放下, 看要不要芳
                    dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                System.out.print(dp[i][v] + " ");
            }
            System.out.println();
        }

        int maxValue = -1;
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                if (maxValue < dp[i][v]) {
                    maxValue = dp[i][v];
                }
            }
        }
        System.out.println(maxValue);
    }


}
