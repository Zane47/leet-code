package leetcode.twopoints;

import javax.naming.Context;
import java.util.Arrays;

/**
 * 给你一个按非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 */
public class SortedSquares_977 {


    public static void main(String[] args) {
        int[] nums = new int[] {-4, -1, 0, 3, 10};

        System.out.println(Arrays.toString(new Solution().sortedSquares(nums)));
    }

    /**
     * two points
     * 利用数组特征：非递减
     * 如果全都是正数，那么平方后的循序不变
     * 如果全都是负数，那么平方后的循序倒叙
     * 找到分界线index，那么平方后每个部分都是有序(正序or倒序)的
     * nums[0] ~ nums[index] 平方后递减, nums[index+1] ~ nums[n-1]平方后递增
     *
     * 再使用归并排序:
     * 具体地，使用两个指针分别指向位置 index 和 index+1，每次比较两个指针对应的数，选择较小的那个放入答案并移动指针。
     * 当某一指针移至边界时，将另一指针还未遍历到的数依次放入答案。
     */
    static class Solution {
        public int[] sortedSquares(int[] nums) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    index = i;
                } else {
                    break;
                }
            }

            int v1 = index;
            int v2 = index + 1;

            int[] result = new int[nums.length];
            int indexOfResult = 0;
            while (v1 >= 0 && v2 < nums.length) {
                if (nums[v1]) {

                }
                else if () {

                }
                v1--;
                v2++;
                indexOfResult++;
            }

            while (v1 >= 0) {
                result[indexOfResult] = nums[v1];
                v1--;
                indexOfResult++;
            }
            while (v2 < nums.length) {
                result[indexOfResult] = nums[v2];
                v2++;
                indexOfResult++;
            }
            return result;
        }
    }

    /**
     * 直接法
     */
    static class Solution1 {
        public int[] sortedSquares(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) Math.pow(nums[i], 2);
            }
            Arrays.sort(nums);
            return nums;
        }
    }


}
