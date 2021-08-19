package leetcode.twopoints;

import java.awt.font.NumericShaper;
import java.util.Arrays;


/**
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 *
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class TwoSum2InputArrayIsSorted_167 {

    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(new Solution().twoSum(nums, target)));
    }

    /**
     * 双指针  缩减搜索区间的方法
     * 为什么不会漏掉情况？
     * 1：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/
     *
     * 2：
     * 使用双指针的实质是缩小查找范围。那么会不会把可能的解过滤掉？
     * 答案是不会。假设 numbers[i]+numbers[j]=target 是唯一解，
     * 其中 0 ≤ i < j ≤ numbers.length−1。
     * 初始时两个指针分别指向下标 0 和下标 numbers.length−1，
     * 左指针指向的下标小于或等于 i，右指针指向的下标大于或等于 j。
     * 除非初始时左指针和右指针已经位于下标 i 和 j，
     * 否则一定是左指针先到达下标 i 的位置或者右指针先到达下标 j 的位置。
     *
     * 如果左指针先到达下标 i 的位置，
     * 此时右指针还在下标 j 的右侧，sum>target，因此一定是右指针左移，左指针不可能移到 i 的右侧。
     *
     * 如果右指针先到达下标 j 的位置，此时左指针还在下标 i 的左侧，sum < target，
     * 因此一定是左指针右移，右指针不可能移到 j 的左侧。
     *
     * 由此可见，在整个移动过程中，左指针不可能移到 i 的右侧，右指针不可能移到 j 的左侧，因此不会把可能的解过滤掉。
     * 由于题目确保有唯一的答案，因此使用双指针一定可以找到答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     */
    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    return new int[] {left + 1, right + 1};
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }

            return new int[] {-1, -1};
        }
    }

    /**
     * 暴力
     */
    static class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            int length = numbers.length;
            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (numbers[i] + numbers[j] == target) {
                        return new int[] {i + 1, j + 1};
                    }
                }
            }
            return new int[] {-1, -1};
        }
    }


}
