package leetcode.dp.bag;

/**
 * 完全背包
 */
public class CompleteBagTest {
    public static void main(String[] args) {
        // 先遍历物品，再遍历背包
        testCompletePack();



        // 先遍历背包，再遍历物品
        testCompletePackAnotherWay();
    }

    //先遍历物品，再遍历背包
    private static void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++){
            // 01背包内嵌的循环是从大到小遍历，为了保证每个物品仅被添加一次。
            // 而完全背包的物品是可以添加多次的，所以要从小到大去遍历，即：
            for (int j = 1; j <= bagWeight; j++){
                if (j - weight[i] >= 0){
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        for (int maxValue : dp){
            System.out.println(maxValue + "   ");
        }
    }

    //先遍历背包，再遍历物品
    private static void testCompletePackAnotherWay(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 1; i <= bagWeight; i++){
            for (int j = 0; j < weight.length; j++){
                if (i - weight[j] >= 0){
                    dp[i] = Math.max(dp[i], dp[i - weight[j]] + value[j]);
                }
            }
        }
        for (int maxValue : dp){
            System.out.println(maxValue + "   ");
        }
    }

}


