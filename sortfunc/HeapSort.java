package leetcode.sortfunc;

public class HeapSort {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.sortArray(new int[]{1, 65, 8, 46, 88, 45, 47, 66});
    }

    static class Solution {
        public int[] sortArray(int[] nums) {
            heapSort(nums);
            return nums;
        }

        public void heapSort(int[] nums) {
            int length = nums.length - 1;
            buildMaxHeap(nums, length);
            for (int i = length; i >= 1; --i) {
                swap(nums, i, 0);
                length -= 1;
                maxHeapify(nums, 0, length);
            }
        }

        public void buildMaxHeap(int[] nums, int length) {
            for (int i = length / 2; i >= 0; --i) {
                maxHeapify(nums, i, length);
            }
        }

        public void maxHeapify(int[] nums, int i, int length) {
            for (; (i << 1) + 1 <= length;) {
                int lson = (i << 1) + 1;
                int rson = (i << 1) + 2;
                int large;
                if (lson <= length && nums[lson] > nums[i]) {
                    large = lson;
                } else {
                    large = i;
                }
                if (rson <= length && nums[rson] > nums[large]) {
                    large = rson;
                }
                if (large != i) {
                    swap(nums, i, large);
                    i = large;
                } else {
                    break;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


}
