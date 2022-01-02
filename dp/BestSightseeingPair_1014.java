package leetcode.dp;

/**
 * 给你一个正整数数组 values，其中 values[i]表示第 i 个观光景点的评分，
 * 并且两个景点i和j之间的距离为j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，
 * 也就是景点的评分之和 减去 它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 * <p>
 * 示例 1：
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * <p>
 * <p>
 * 示例 2：
 * 输入：values = [1,2]
 * 输出：2
 */
public class BestSightseeingPair_1014 {
    public static void main(String[] args) {
        int[] values = new int[]{8, 1, 5, 2, 6};

//        int[] values = new int[]{1, 2};


        System.out.println(new Solution2().maxScoreSightseeingPair(values));
    }

    /**
     * 优化利用score来代替dp数组用到了贪心算法
     *
     * 优化空间复杂度
     *
     */
    static class Solution {
        public int maxScoreSightseeingPair(int[] values) {
            int max = 0;
            int score = values[0] + 0;

            for (int i = 1; i < values.length; i++) {
                max = Math.max(max, score + values[i] - i);

                score = Math.max(score, values[i] + i);
            }

            return max;
        }
    }


    /**
     * dp的方法:
     * 维护一个dp数组用来记录到达从0到i的最大景点得分的值, i+values[i]的最大值
     * 再来一个变量max，滚动记录最大值
     * <p>
     * 状态转化方程：
     * dp[i] = Math.max(dp[i-1], i + values[i])
     * <p>
     * <p>
     * 滚动数组中的变量：
     * max = Math.max(max,dp[i-1] + values[i] - i)
     *
     *
     *
     * 因为对于values[j]来说，
     * 我们只要知道它前面最大的那个 values[i] + i -j就行了！！
     *
     * 然而对于 j 而言，values[j]和j都是固定的所以，
     * 只需要在[0,j-1]中找到最大的values[i] + i就可以了
     *
     * 所以最终问题就来到了传统dp问题~
     * 一维数组dp[i]表示前i-1个元素中，values[i] + i的最大值
     */
    static class Solution2 {
        public int maxScoreSightseeingPair(int[] values) {
            int[] dp = new int[values.length];
            dp[0] = values[0];

            int max = 0;

            for (int i = 1; i < values.length; i++) {
                // 当前最大得分
                max = Math.max(max, dp[i - 1] + values[i] - i);

                // 动态转化方程
                dp[i] = Math.max(dp[i - 1], values[i] + i);
            }

            return max;
        }
    }


    /**
     * 从暴力法可以看出:
     * 在每次计算过程中,在每个 j 都固定 A[j] -j 计算它前面的 A[i] + i 的最大值 (i : 1 -> j)
     * <p>
     * 贪心算法: 将问题分解为 A[i] + i 和 A[j] - j 的最大值  i < j
     * 那我们在一次遍历 j 的时候只需要不断保存并且更新 A[i] + i 的值即可求出最大值
     *
     * // ------------------------ 理解 ------------------------
     * 得分
     * = values[i] + values[j] + i - j
     * = values[y] - y + (values[i] + i)
     * 因为i < y
     * 所以: 在y遍历时可以找到前面最大的mvp = (values[i] + i)
     * 然后就没有然后了,记录得分最大值即可呀
     */
    static class Solution1 {
        public int maxScoreSightseeingPair(int[] values) {
            int max = Integer.MIN_VALUE;
            int left = values[0];

            for (int j = 1; j < values.length; j++) {
                max = Math.max(max, values[j] - j + left);  //更新 最大值

                left = Math.max(left, values[j] + j);  //更新 A[i] + i
            }

            return max;
        }
    }


    /**
     * 暴力
     * n^2的时间复杂度
     */
    static class Solution0 {
        public int maxScoreSightseeingPair(int[] values) {
            int max = 0;

            for (int i = 0; i < values.length; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    int value = values[i] + values[j] + i - j;
                    if (value > max) {
                        max = value;
                    }
                }
            }
            return max;
        }
    }

}
