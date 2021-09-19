package leetcode.divide;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */

public class MajorityElement_169 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3};

        System.out.println(new Solution().majorityElement(nums));

    }

    /**
     * 分治:
     * 将数组分成左右两部分，分别求出左半部分的众数 a1 以及右半部分的众数 a2，
     * 随后在 a1 和 a2 中选出正确的众数。
     *
     *
     * 如果数 a 是数组 nums 的众数，
     * 如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * 我们可以使用反证法来证明这个结论。
     * 假设 a 既不是左半部分的众数，也不是右半部分的众数，
     * 那么 a 出现的次数少于 l / 2 + r / 2 次，
     * 其中 l 和 r 分别是左半部分和右半部分的长度。
     * 由于 l / 2 + r / 2 <= (l + r) / 2，
     * 说明 a 也不是数组 nums 的众数，因此出现了矛盾。所以这个结论是正确的。
     *
     * 分治算法递归求解，直到所有的子问题都是长度为 1 的数组
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    // todo: 并不懂
    static class Solution {
        public int majorityElement(int[] nums) {

            return majorityElementRec(nums, 0, nums.length - 1);
        }

        private int majorityElementRec(int[] nums, int low, int high) {
            // base case; the only element in an array of size 1 is the majority
            // element.
            if (low == high) {
                return nums[low];
            }
            // recurse on left and right halves of this slice.
            int mid = (low + high) / 2;
            mid = (high - low) / 2 + low;
            int left = majorityElementRec(nums, low, mid);
            int right = majorityElementRec(nums, mid+1, high);
            // if the two halves agree on the majority element, return it.
            if (left == right) {
                return left;
            }

            // otherwise, count each element and return the "winner".
            int leftCount = countInRange(nums, left, low, high);
            int rightCount = countInRange(nums, right, low, high);

            return leftCount > rightCount ? left : right;
        }

        private int countInRange(int[] nums, int num, int low, int high) {

            int count = 0;
            for (int i = low; i <= high; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;



        }
    }

    /**
     * 最原始的
     * 借用hashmap存储次数
     *
     */
    static class Solution1 {
        public int majorityElement(int[] nums) {
            int result = -1;
            int t = (int) Math.ceil(nums.length / 2);
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[i]) == null) {
                    map.put(nums[i], 1);
                } else {
                    Integer integer = map.get(nums[i]);
                    map.put(nums[i], ++integer);
                }
            }

            for (Integer key : map.keySet()) {
                if (map.get(key) > t) {
                    return key;
                }

            }


            return result;
        }
    }

}
