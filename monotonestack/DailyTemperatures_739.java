package leetcode.monotonestack;


import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Stack;

/**
 *
 * 请根据每日 气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 *
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出:[1,1,1,0]
 *
 *
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class DailyTemperatures_739 {
    public static void main(String[] args) {
        int[] temp = new int[]{73,74,75,71,69,72,76,73};


        System.out.println(Arrays.toString(new Solution().dailyTemperatures(temp)));
    }

    /**
     * 单调栈
     * 动画：通俗易懂
     *
     * 遍历整个数组，如果栈不空，且当前数字大于栈顶元素，
     * 那么如果直接入栈的话就不是 递减栈 ，所以需要取出栈顶元素，
     * 由于当前数字大于栈顶元素的数字，而且一定是第一个大于栈顶元素的数，直接求出下标差就是二者的距离。
     *
     * 继续看新的栈顶元素，直到当前数字小于等于栈顶元素停止，
     * 然后将数字入栈，这样就可以一直保持递减栈，
     * 且每个数字和第一个大于它的数的距离也可以算出来。
     *
     * 作者：MisterBooo
     * 链接：https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/)
     */
    static class Solution {
        // 结合视频理解！！！
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] result = new int[n];
            // 下标入栈即可
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                // 单调减的，如果过来的元素要大于栈顶的话
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    Integer index = stack.pop();
                    // 这里更新的是pop出来的这个下标
                    result[index] = i - index;
                }
                stack.add(i);
            }
            return result;
        }

    }


    /**
     * 输入: [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     *
     * 暴力
     *
     */
    static class Solution1 {
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] result = new int[n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        result[i] = j - i;
                        break;
                    }
                }
            }

            return result;

        }
    }

}
