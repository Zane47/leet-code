package leetcode.difference;

/**
 *
 * 给你一个二维整数数组ranges和两个整数left和right。
 * 每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。
 *
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 *
 *
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *

 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CheckIfAllIntegersAreCovered_1893 {


    public static void main(String[] args) {
        int[][] ranges = new int[][]{
            {1, 2},
            {3, 4},
            {5, 6}
        };
        int left = 2;
        int right = 5;

        System.out.println(new Solution().isCovered(ranges, left, right));
    }

    /**
     * 参考这个，图片理解
     * https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered/solution/yi-ti-san-jie-bao-li-you-hua-chai-fen-by-w7xv/
     *
     * 差分数组diff表示相邻格之间，是否被覆盖的变化量。
     * diff[i]++,代表在i位置上有新的覆盖
     * 若覆盖到j结束了呢？此时j依然是覆盖，但是j+1不在覆盖状态，所以在j+1处 -1；
     * 即diff[j+1]--;  （所以这里要多一位 52）
     *
     * 当我们把差分数组求前缀和，就很直观把这种变化量转化为不变的，可以理解的。
     * 前缀和sum[i]的大小，就代表被覆盖次数，
     *
     * 差分数组前缀和不仅能查询是否被覆盖，还能查询某一区间被覆盖几次
     *
     */
    static class Solution {
        public boolean isCovered(int[][] ranges, int left, int right) {
            int[] diff = new int[52];
            // 处理差分数组
            for (int i = 0; i < ranges.length; i++) {
                diff[ranges[i][0]]++;
                // 注意这里是后一位-1, j+1不在覆盖状态，所以在j+1处 -1
                diff[ranges[i][1]+1]--;
            }

            // 求前缀和
            int[] sum = new int[52];
            for (int i = 1; i < 52; i++) {
                sum[i] = sum[i-1] + diff[i];
            }

            // 判断是否满足
            for (int i = left; i <= right; i++) {
                if (sum[i] <= 0) {
                    return false;
                }
            }
            return true;
        }

    }


    /**
     * 暴力
     */
    static class Solutionbaoli {
        public boolean isCovered(int[][] ranges, int left, int right) {
            boolean[] isCovered = new boolean[51];
            for (int i = 0; i < ranges.length; i++) {
                for (int j = ranges[i][0]; j <= ranges[i][1]; j++) {
                   isCovered[j] = true;
               }

            }

            for (int i = left; i <= right; i++) {
                if (!isCovered[i]) {
                    return false;
                }
            }
            return true;
        }
    }

}
