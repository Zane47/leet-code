package leetcode;

// lc31
// 必须原地修改

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */

import java.util.Arrays;

/**什么是字典序
 * 对于数字1、2、3…n的排列，不同排列的先后关系是从左到右逐个比较对应的数字的先后来决定的。
 * 例如对于5个数字的排列 12354和12345，排列12345在前，排列12354在后。
 * 按照这样的规定，5个数字的所有的排列中最前面的是12345，最后面的是 54321。
 * 例如
 * 123456
 * 123465
 * 123546
 * ...
 * 654321
 * 有关系为：123456 < 123465 < 123546 < ... < 654321
 *
 * 求下一个排列的思路：
 * 1.我们要下一个数字比当前的数字要大。因此只需要将后面的大数与前面的小数交换，就可以得到一个更大的数
 * 例如：123456，5和6交换就可以得到一个更大的数字123465
 * 2.我们希望下一个数字增加的幅度尽可能的小，“紧密相连”
 *
 * 所以步骤如下：
 * 1.从尽可能靠右的低位进行交换，需要从后往前找
 *
 * 2.交换的数字要尽可能小，例如123465，需要5和4交换(123564)，而不是6和4交换(123645)
 *
 * 3.将大数换到前面后，需要将大数后面的所有数重置为升序，升序排列就是最小的排列。
 * 以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；
 * 然后需要将 5 之后的数重置为升序，得到 123546。
 * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
 *
 *
 */
public class nextPermutation31 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // int[] nums = {1, 2, 3};
        int[] nums = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        solution.nextPermutation(nums);

    }


    /** https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
     * 1. **从后向前**查找第一个**相邻升序**的元素对 (i,j)，满足 A[i] < A[j]。
     * 此时 [j,end) 必然是降序
     *
     * 2. 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。
     * A[i]、A[k] 分别就是上文所说的「小数」、「大数」
     *
     * 3. 将 A[i] 与 A[k] 交换
     *
     * 4. 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
     *
     * 5. 如果在步骤 1 找不到符合的相邻元素对，
     * 说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
     *
     * e.g.:
     * step1: 1 5 8 4 7 6 5 3 1
     * 从后往前找到第一个相邻，
     *
     *
     *
     *
     *
     */
    static class Solution {
        public void nextPermutation(int[] nums) {
            int length = nums.length;
            for (int i = length - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    //步骤中 J 就是此处i，I就是此处i-1
                    for (int j = length - 1; j >= i; j--) {
                        if (nums[i - 1] < nums[j]) {
                            swap(nums, i - 1, j);
                            break;
                        }
                    }
                    Arrays.sort(nums, i, length);
                    return;
                }
            }

            Arrays.sort(nums);
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
