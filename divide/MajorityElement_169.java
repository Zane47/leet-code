package leetcode.divide;

import java.util.Arrays;
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
        int[] nums = new int[]{2,2,1,1,1,2,2};

        System.out.println(new Solution().majorityElement(nums));

    }


    /**
     * 分治
     * 此题作为分治法的练习题是不错的，
     * 分治法的思路在与把多个大问题化解为单位为1或可直接解决的小问题，
     * 将小问题解决后进行递归处理，重新合并为大问题的答案。
     * 此题为求众数问题，根据众数定义可知，将一个数组分为两个部分，
     * 其中一个数组的众数必定为大数组中的众数。
     *
     * 所以在此题中，我们先设置递归条件 = 头指针等于尾指针时，返回数组中的这个数。
     * 递归过程如下：首先我们需要将代码分为两个部分，然后求出两个数组中的众数，
     * 分别对其进行次数出现的统计（注意统计范围为左数组+右数组），最后将出现次数大的进行返回。
     *
     *
     * 这道题用分治法会有极大的时间复杂度与空间复杂度，所以本题只适合作为练习题入手，
     * 通过此题可以对分治法的过程有个大概的了解，分治法的精髓在于返回条件的设置，以及递归过程的处理。
     *
     * 作者：ander-a
     * 链接：https://leetcode-cn.com/problems/majority-element/solution/fen-zhi-fa-by-ander-a-e3na/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    static class Solution {
        public int majorityElement(int[] nums) {
            return search(nums, 0, nums.length - 1);
        }

        private int search(int[] nums, int head, int tail) {
            // 递归结束条件
            if (head == tail) {
                return nums[head];
            }

            int mid = (head + tail) / 2;

            // 递归求出出现最多的数字是什么
            int leftMostNum = search(nums, head, mid);
            int rightMostNum = search(nums, mid + 1, tail);

            // 分别求个数，然后比大小
            int countOfLeftMostNum = calcCount(nums, leftMostNum, head, tail);
            int countOfRightMostNum = calcCount(nums, rightMostNum, head, tail);


            return countOfLeftMostNum >= countOfRightMostNum ? leftMostNum : rightMostNum;


        }

        private int calcCount(int[] nums, int target, int head, int tail) {
            int count = 0;
            // 这里要加=，要全部都算，前闭后闭
            for (int i = head; i <= tail; i++) {
                if (nums[i] == target) {
                    count++;
                }
            }
            return count;
        }
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
    static class Solution3 {
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
     * 排序
     * 已经知道了大于n/2，所以中位数就是
     */
    static class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
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
