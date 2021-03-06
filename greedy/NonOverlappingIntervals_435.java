package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**-> 452类似
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NonOverlappingIntervals_435 {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1,2}, {2,3}, {3,4}, {1,3}
        };

        System.out.print(new Solution().eraseOverlapIntervals(intervals));
    }

    /**
     *
     * 按照右边界升序排序, 然后求出来最多不重叠的那些, 那么总数减去最多不重叠的那些, 剩下的就是最少移除的数量
     * https://labuladong.gitbook.io/algo/mu-lu-ye-2/mu-lu-ye-3/tan-xin-suan-fa-zhi-qu-jian-tiao-du-wen-ti
     *
     * 按照右边界排序还是按照左边界排序, 见这个:https://leetcode-cn.com/problems/non-overlapping-intervals/solution/435-wu-zhong-die-qu-jian-tan-xin-jing-di-qze0/
     *
     */
    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 求最多不相交的区间, 用总数减, 就是最少移除的区间了
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // 按照右边界升序
                    return o1[1] - o2[1];
                }
            });
            // 至少有一个区间不相交
            int count = 1;
            // 第一个的末尾
            int xEnd = intervals[0][1];
            for (int[] interval : intervals) {
                // 下一个的左边界大于等于前一个的右边界, 那就++, 做更新
                // 相等的情况也可以
                if (interval[0] >= xEnd) {
                    count++;
                    xEnd = interval[1];
                }
            }
            // 上面求的是最多不相交的区间, 用总数减, 就是最少移除的区间了
            return intervals.length - count;
        }
    }



}
