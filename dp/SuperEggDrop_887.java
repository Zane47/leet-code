package leetcode.dp;

/**
 * 鸡蛋掉落
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，
 * 从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用这枚鸡蛋。
 * <p>
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * <p>
 * 示例 1：
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * <p>
 * 示例 2：
 * 输入：k = 2, n = 6
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：k = 3, n = 14
 * 输出：4
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484675&idx=1&sn=4a4ac1c0f1279530b42fedacc6cca6e6&chksm=9bd7fb0baca0721dda1eaa1d00b9a520672dc9d5c3be762eeca869be35d7ce232922ba8e928b&scene=21#wechat_redirect
 * ## 思路:
 * 最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层F呢？
 * 理解其中的"最坏情况", "至少"
 * 不考虑鸡蛋个数的限制, 假设有7层楼
 * * 最坏情况: 鸡蛋破碎一定发生在搜索区间穷尽时, 如果是线性搜索, 那么就是从第一楼一直到第7层, f返回7
 * * 至少: 如果用二分, 先去(1+7)/2 = 4层去扔,
 * 如果碎了 -> (1+3)/2 = 2层去扔
 * 如果没碎 -> (5+7)/2 = 6层去扔
 * ...
 * 最坏情况应该是试到第 7 层鸡蛋还没碎（F = 7），或者鸡蛋一直碎到第 1 层（F = 0）。
 * 然而无论那种最坏情况，只需要试log7向上取整等于 3 次，比刚才的 7 次要少，这就是所谓的至少要扔几次。
 * PS：这有点像 Big O 表示法计算算法的复杂度。
 * <p>
 * ## base case -> 明确[状态] -> 明确[选择] -> 定义dp数组含义
 * ###
 * 状态: 楼层N和鸡蛋个数K
 * 选择: 去哪层扔鸡蛋, 1. 线性扫描楼层, 2. 二分查找. 不同的方法会有不同的状态转移方程
 * <p>
 * 思路: 二维的dp数组或者带有两个状态参数的dp函数来表示状态转移；
 * 外加一个 for 循环来遍历所有选择，择最优的选择更新结果
 * ```
 * # 当前状态为 (K 个鸡蛋，N 层楼)
 * # 返回这个状态下的最优结果
 * def dp(K, N):
 * int res
 * for 1 <= i <= N:
 * res = min(res, 这次在第 i 层楼扔鸡蛋)
 * return res
 * (这段伪码还没有展示递归和状态转移，不过大致的算法框架已经完成了。)
 * ```
 * ###
 * 我们在第i层楼扔了鸡蛋之后，可能出现两种情况：鸡蛋碎了，鸡蛋没碎。这时候状态转移就来了：
 * 如果鸡蛋碎了，那么鸡蛋的个数K应该减一，搜索的楼层区间应该从[1..N]变为[1..i-1]共i-1层楼；
 * 如果鸡蛋没碎，那么鸡蛋的个数K不变，搜索的楼层区间应该从 [1..N]变为[i+1..N]共N-i层楼。
 * ps: 在第i层楼扔鸡蛋如果没碎，楼层的搜索区间缩小至上面的楼层，是不是应该包含第i层楼呀？
 * 不必，因为已经包含了。开头说了 F 是可以等于 0 的，向上递归后，第i层楼其实就相当于第 0 层，可以被取到，所以说并没有错误。\
 * <p>
 * 最坏情况: 在做选择的时候, 要看鸡蛋碎了还是没有碎, 看这两种情况下哪种结果更大
 * ```
 * def dp(K, N):
 * for 1 <= i <= N:
 * # 最坏情况下的最少扔鸡蛋次数
 * res = min(res,
 * max(
 * dp(K - 1, i - 1), # 碎了, 向下层找
 * dp(K, N - i)      # 没碎, 向上层找
 * ) + 1 # 在第 i 楼扔了一次
 * )
 * return res
 * ```
 * <p>
 * ### base case
 * // 楼层0的时候, 不需要扔鸡蛋了
 * N = 0 -> return 0
 * // 鸡蛋数量为1, 那么就只能线性扫描了
 * K = 1 -> return N
 */
public class SuperEggDrop_887 {
    public static void main(String[] args) {

        System.out.print(new Solution().superEggDrop(1, 2));

    }

    /**
     * 会超时
     * 最后执行的输入：
     * 6
     * 10000
     * <p>
     * dp(k,n) = min{max{dp(k-1, i-1), dp(k, n-i)} + 1}
     * <p>
     * 动态规划算法的时间复杂度就是子问题个数 × 函数本身的复杂度。
     * 函数本身的复杂度就是忽略递归部分的复杂度，这里 dp 函数中有一个 for 循环，所以函数本身的复杂度是 O(N)。
     * <p>
     * 子问题个数也就是不同状态组合的总数，显然是两个状态的乘积，也就是 O(KN)。
     * <p>
     * 所以算法的总时间复杂度是 O(K*N^2), 空间复杂度 O(KN)。
     *
     * todo: 超时优化 -> https://leetcode-cn.com/problems/super-egg-drop/solution/ji-ben-dong-tai-gui-hua-jie-fa-by-labuladong/
     *
     */
    static class Solution {
        public int superEggDrop(int k, int n) {
            // memo
            int[][] memo = new int[k + 1][n + 1];
            // 二维数组的初始化
            for (int i = 0; i < k + 1; i++) {
                Arrays.fill(memo[i], -1);
            }
            // dp: 鸡蛋数量k, 楼层数量n的情况下, 最坏情况的至少尝试次数
            return dp(k, n, memo);
        }

        private int dp(int k, int n, int[][] memo) {
            if (k == 1) {
                return n;
            }
            if (n == 0) {
                return 0;
            }
            if (memo[k][n] != -1) {
                return memo[k][n];
            }

            /**
             * for 1 <= i <= N:
             *    res = min(res, 这次在第 i 层楼扔鸡蛋)
             * return res
             */
            // 遍历楼层
            int result = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                // 计算这次在那一层扔鸡蛋, 最坏的情况, 那就是要计算鸡蛋从这层扔下来, 碎或者没碎哪个最终结果更大
                // 碎了的话, 鸡蛋数量-1, 从更低楼层开始扔
                // 没碎的话, 鸡蛋数量不变, 从更高楼层开始扔
                int max = Math.max(dp(k - 1, i - 1, memo), dp(k, n - i, memo)) + 1;
                // "至少"
                result = Math.min(result, max);


                // result = Math.min(result, Math.max(dp(k - 1, i - 1, memo), dp(k, n - i, memo)) + 1);
            }
            memo[k][n] = result;
            return result;
        }
    }

}
