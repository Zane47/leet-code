package leetcode.dp.bag;

import java.util.Arrays;
import java.util.Scanner;

/**ac
 * https://www.nowcoder.com/questionTerminal/95329d9a55b94e3fb2da475d3d052164
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/95329d9a55b94e3fb2da475d3d052164
 * 来源：牛客网
 * <p>
 * 你打开了美了么外卖，选择了一家店，你手里有一张满 X 元减 10 元的券，
 * 店里总共有 n 种菜，第 i 种菜一份需要 Ai 元，因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，
 * 现在问你最少需要选择多少元的商品才能使用这张券。
 * <p>
 * 1<=n,Ai<=100, 1<=X<=10000
 * <p>
 * input:
 * 第一行两个正整数n和X，分别表示菜品数量和券的最低使用价格。
 * 接下来一行n个整数，第i个整数表示第i种菜品的价格。
 * <p>
 * output:
 * 一个数，表示最少需要选择多少元的菜才能使用这张满X元减10元的券，保证有解。
 * <p>
 * 5 20
 * 18 19 17 6 7
 * <p>
 * -> 23
 * <p>
 * 只能选一个, 01背包问题
 * 最少需要选择多少元的商品才能使用这张券。
 * <p>
 * 要求>=X的最小值
 * 之前的01背包是<=X的最大值
 * 这里做一个转换即可
 * 求剩下的菜, <=sum-X的最大值
 */
public class TakeOutCoupon_meituan {
    public static void main(String[] args) {
        // 二维dp
        // solution1();

        // 一维dp
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int X = sc.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
        }

        int V = sum - X;

        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // 01背包, 一维务必倒序
            for (int v = V; v >= nums[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - nums[i]] + nums[i]);
            }
        }
        System.out.println(sum - dp[V]);

    }

    /**
     * 二维dp
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int X = sc.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
        }

        int V = sum - X;

        // 前i个物品恰好凑成V的最大价值
        // dp[n][v]: 前n个物品, 重量为v的最大价值
        int[][] dp = new int[n + 1][V + 1];

        // base case
        // dp[0][...]
        Arrays.fill(dp[0], 0);
        // dp[...][0]
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int v = 1; v <= V; v++) {
                if (nums[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    dp[i][v] = Math.max(
                            dp[i - 1][v],
                            dp[i - 1][v - nums[i - 1]] + nums[i - 1]);
                }
            }
        }


        System.out.println(sum - dp[n][V]);
    }
}
