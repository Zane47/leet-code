package leetcode.sortfunc.mergesort;

import java.util.Arrays;

/**
 * https://www.jiuzhang.com/solution/merge-sort
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * 因为需要合并, 所以要开辟n的空间
 * 递归的写法
 *
 * !!! 执行流程见图: https://images2015.cnblogs.com/blog/1024555/201612/1024555-20161218163120151-452283750.png
 *
 */
public class MergeSort_jiuzhang {
    public static void main(String[] args) {
        int[] nums = {8, 4, 5, 7, 1, 3, 6, 2};


        new Solution().mergeSort(nums);

        System.out.println(Arrays.toString(nums));
    }


    static class Solution {
        public void mergeSort(int[] nums) {
            // use a shared temp array, the extra memory is O(n) at least
            // 归并的时候使用
            int[] temp = new int[nums.length];

            mergeSortFunc(nums, 0, nums.length - 1, temp);
        }

        private void mergeSortFunc(int[] nums, int start, int end, int[] temp) {
            if (start >= end) {
                return;
            }

            int mid = (start + end) / 2;

            // 左边归并排序，使得左子序列有序
            mergeSortFunc(nums, start, mid, temp);

            // 右边归并排序，使得右子序列有序
            mergeSortFunc(nums, mid + 1, end, temp);

            // 合并
            merge(nums, start, end, temp);
        }


        private void merge(int[] nums, int start, int end, int[] temp) {
            int mid = (start + end) / 2;
            int leftIndex = start;
            int rightIndex = mid + 1;
            int tempIndex = leftIndex;

            while (leftIndex <= mid && rightIndex <= end) {
                if (nums[leftIndex] < nums[rightIndex]) {
                    temp[tempIndex++] = nums[leftIndex++];
                } else {
                    temp[tempIndex++] = nums[rightIndex++];
                }
            }

            while (leftIndex <= mid) {
                temp[tempIndex++] = nums[leftIndex++];
            }

            while (rightIndex <= end) {
                temp[tempIndex++] = nums[rightIndex++];
            }

            for (int i = start; i <= end; i++) {
                nums[i] = temp[i];
            }
            
        }
    }
}
