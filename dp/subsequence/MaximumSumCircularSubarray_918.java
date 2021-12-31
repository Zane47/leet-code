package leetcode.dp.subsequence;

/**
 * 给定一个由整数数组 A表示的环形数组 C，求 C的非空子数组的最大可能和。
 * <p>
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。
 * <p>
 * (形式上，当0 <= i < A.length时C[i] = A[i], 且当i >= 0时C[i+A.length] = C[i])
 * <p>
 * 此外，子数组最多只能包含固定缓冲区 A中的每个元素一次。
 * (形式上，对于子数组C[i], C[i+1], ..., C[j],
 * 不存在i <= k1, k2 <= j 其中k1 % A.length = k2 % A.length)
 * <p>
 * <p>
 * 示例 1：
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * <p>
 * 示例 2：
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * <p>
 * 示例 3：
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * <p>
 * <p>
 * 示例 4：
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * <p>
 * 示例 5：
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 */
public class MaximumSumCircularSubarray_918 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, -1, 2, -1};

        System.out.println(new Solution().maxSubarraySumCircular(nums));
    }

    static class Solution {
        public int maxSubarraySumCircular(int[] nums) {


            return 0;
        }
    }
}
