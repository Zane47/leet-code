package leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 把所有可能的凑硬币方法都穷举出来，然后找找看最少需要多少枚硬币。
 * -> pdd的第一题, 切绳子, 这里是要总数, pdd的是要具体的每一个的个数
 */
public class CoinChange_322 {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;

        /*int[] coins = new int[]{2};
        int amount = 3;*/

        System.out.print(new Solution().coinChange(coins, amount));
    }

    /**
     * dp 数组的迭代解法
     * 自底向上
     * <p>
     * dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
     * 目标金额i, 凑出目标金额的最少硬币总数dp[i]
     *
     * 为啥 dp 数组初始化为 amount + 1 呢，
     * 因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），
     * 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
     * 为啥不直接初始化为 int 型的最大值 Integer.MAX_VALUE 呢？
     * 因为后面有 dp[i - coin] + 1，这就会导致整型溢出。
     * todo: ???
     */
    static class Solution {
        public int coinChange(int[] coins, int amount) {
            // dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
            int[] dp = new int[amount + 1];
            // 数组大小为 amount + 1，初始值也为 amount + 1
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            // 外循环遍历所有状态的所有取值
            for (int i = 0; i < dp.length; i++) {
                // 内循环遍历所有选择的最小值
                for (int coin : coins) {
                    // 子问题无解，跳过
                    if (i - coin < 0) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }

            }
            return (dp[amount] == amount + 1) ? -1 : dp[amount];
        }
    }


    /**
     * dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
     * 带备忘录的递归
     * 执行用时：24 ms, 在所有 Java 提交中击败了17.16%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了7.76%的用户
     */
    static class Solution3 {

        private int[] memo;

        public int coinChange(int[] coins, int amount) {
            // 0到本身, length+1
            memo = new int[amount + 1];
            Arrays.fill(memo, -2);
            return dp(coins, amount);
        }

        /**
         * dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
         * 目标金额n, 凑出目标金额n的最少硬币数量为dp(n)
         *
         * @param coins
         * @param amount
         * @return
         */
        private int dp(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }

            if (memo[amount] != -2) {
                return memo[amount];
            }

            int min = Integer.MAX_VALUE;

            for (int coin : coins) {
                // 子问题的结果
                int subProblem = dp(coins, amount - coin);
                // 子问题无解, 跳过
                if (subProblem == -1) {
                    continue;
                }
                // 在子问题中选择最优解，然后加一
                min = Math.min(min, subProblem + 1);
            }

            if (min == Integer.MAX_VALUE) {
                memo[amount] = -1;
            } else {
                memo[amount] = min;
            }

            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }


    /**
     * 暴力
     * 递归的写法, 自顶向下
     * <p>
     * 1, 2, 5
     * 100
     * time-out
     * <p>
     * dp(n) =
     * 1. n=0, dp(n) = 0
     * 2. n<0, dp(n) = -1
     * 3. n>0, dp(n) = min{dp(n-coin)+1, coin 属于coins}
     * <p>
     * 有很多重叠的部分, 例如: [1, 2, 5], 11
     * <p>
     * <p>
     * https://labuladong.gitbook.io/algo/mu-lu-ye-2/mu-lu-ye/dong-tai-gui-hua-xiang-jie-jin-jie
     * <p>
     * 硬币数量无限
     * 1、确定 base case，这个很简单，显然目标金额 amount 为 0 时算法返回 0，因为不需要任何硬币就已经凑出目标金额了。
     * <p>
     * 2、确定「状态」，也就是原问题和子问题中会变化的变量。由于硬币数量无限，硬币的面额也是题目给定的，只有目标金额会不断地向 base case 靠近，所以唯一的「状态」就是目标金额 amount。
     * <p>
     * 3、确定「选择」，也就是导致「状态」产生变化的行为。目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，就相当于减少了目标金额。所以说所有硬币的面值，就是你的「选择」。
     * <p>
     * 4、明确 dp 函数/数组的定义。我们这里讲的是自顶向下的解法，所以会有一个递归的 dp 函数，一般来说函数的参数就是状态转移中会变化的量，也就是上面说到的「状态」；函数的返回值就是题目要求我们计算的量。就本题来说，状态只有一个，即「目标金额」，题目要求我们计算凑出目标金额所需的最少硬币数量。所以我们可以这样定义 dp 函数：
     * <p>
     * dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
     * <p>
     * // 伪码框架
     * int coinChange(int[] coins, int amount) {
     * // 题目要求的最终结果是 dp(amount)
     * return dp(coins, amount)
     * }
     * <p>
     * // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
     * int dp(int[] coins, int n) {
     * // 做选择，选择需要硬币最少的那个结果
     * for (int coin : coins) {
     * res = min(res, 1 + dp(n - coin))
     * }
     * return res
     * }
     */
    static class Solution2 {
        public int coinChange(int[] coins, int amount) {
            return dp(coins, amount);
        }

        /**
         * dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
         *
         * @param coins
         * @param amount
         * @return
         */
        private int dp(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }

            int min = Integer.MAX_VALUE;

            for (int coin : coins) {
                // 子问题的结果
                int subProblem = dp(coins, amount - coin);
                // 子问题无解, 跳过
                if (subProblem == -1) {
                    continue;
                }
                // 在子问题中选择最优解，然后加一
                min = Math.min(min, subProblem + 1);
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }

    /**
     * 暴力超时, 记录全部的再比较
     * 1, 2, 5
     * 100
     * time-out
     * <p>
     * dfs, 优先用最大的来做
     */
    static class Solution1 {
        public int coinChange(int[] coins, int amount) {
            // 先做个排序
            Arrays.sort(coins);
            // 倒序的方法, 但是要转成Integer[]
            // Arrays.sort(coins, Collections.reverseOrder());

            List<List<Integer>> result = new ArrayList<>();
            // 每个coin的数量
            int[] numsOfCoins = new int[coins.length];

            dfs(amount, numsOfCoins, result, coins);

            if (result.isEmpty()) {
                return -1;
            }

            int minNum = Integer.MAX_VALUE;
            for (List<Integer> integers : result) {
                int sum = 0;
                for (Integer integer : integers) {
                    sum += integer;
                }
                if (sum < minNum) {
                    minNum = sum;
                }
            }

            return minNum;
        }

        private void dfs(int remainingAmount, int[] numsOfCoins,
                         List<List<Integer>> result, int[] coins) {
            if (remainingAmount < 0) {
                return;
            }
            if (remainingAmount == 0) {
                // int[] 转list
                // 如果是包装类型String[], ArrayList<Element> arrayList = new ArrayList<Element>(Arrays.asList(array));
                List<Integer> tempList = Arrays.stream(numsOfCoins).boxed().collect(Collectors.toList());
                if (!result.contains(tempList)) {
                    result.add(tempList);
                }
                return;
            }

            // 先用大的保证他是最少的
            for (int i = coins.length - 1; i >= 0; i--) {
                if (coins[i] <= remainingAmount) {
                    numsOfCoins[i]++;
                    dfs(remainingAmount - coins[i], numsOfCoins, result, coins);
                    // 回溯
                    numsOfCoins[i]--;
                }
            }

        }


    }
}
