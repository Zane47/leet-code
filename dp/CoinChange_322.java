package leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 把所有可能的凑硬币方法都穷举出来，然后找找看最少需要多少枚硬币。
 * -> pdd的第一题, 切绳子
 *
 */
public class CoinChange_322 {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.print(new Solution().coinChange(coins, amount));
    }

    /**
     * dp
     */
    static class Solution {
        public int coinChange(int[] coins, int amount) {
            return 0;
        }
    }

    /**超时的方法
     * 1, 2, 5
     * 100
     *
     *
     * dfs, 优先用最大的来做
     *
     *
     *
     */
    static class Solution1 {
        public int coinChange(int[] coins, int amount) {
            // 先做个排序
            Arrays.sort(coins);

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
                    sum+=integer;
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
            for (int i = coins.length - 1; i >=0; i--) {
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
