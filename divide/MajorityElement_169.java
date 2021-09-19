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
     * 分治
     *
     */
    static class Solution {
        public int majorityElement(int[] nums) {

            return 0;
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
