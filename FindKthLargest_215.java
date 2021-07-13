package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// 215 数组中的第K个最大元素
public class FindKthLargest_215 {

    // 极端情况下：
    // 使用堆最优，k远小于n，时间复杂度O(nlogk)趋近于O(n)，而空间复杂度O(k)则近似于O(1)
    // 剑指offer

    public static void main(String[] args) {

        System.out.println(new Solution4().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }


    // Solution5: 堆排序
    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }
        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int left = i * 2 + 1, right = i * 2 + 2, largest = i;
            if (left < heapSize && a[left] > a[largest]) {
                largest = left;
            }
            if (right < heapSize && a[right] > a[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    // Solution4: 自带的堆priorityqueue，固定大小k的大根堆
    static class Solution4 {
        public int findKthLargest(int[] nums, int k) {
            // 容量为k的大根堆
            PriorityQueue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            for (int i = 0; i < nums.length; i++) {
                heap.add(nums[i]);
            }

            for (int i = 0; i < k - 1; k++) {
                heap.poll();
            }

            return heap.peek();
        }
    }


    // Solution3: 基于快排的quick Select
    // https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
    static class Solution3 {
        public int findKthLargest(int[] nums, int k) {
            int left = 0;
            int right = nums.length - 1;
            // 升序，第 k 大元素的索引是 len - k
            int target = nums.length - k;

            // 枢轴的位置，一定求到了之后就是固定的！！！
            while (true) {
                int partitionIndex = partition(nums, left, right);
                if (partitionIndex == target) {
                    return nums[partitionIndex];
                } else if (partitionIndex < target) {
                    // 在右区间找
                    left = partitionIndex + 1;
                } else {
                    // 在左区间找
                    right = partitionIndex - 1;
                }
            }
            
        }

        /**
         * 在数组 nums 的子区间 [left, right] 执行 partition 操作，
         * 返回 nums[left] 排序以后应该在的位置
         * 在遍历过程中保持循环不变量的语义
         * 1、[left + 1, j] < nums[left]
         * 2、(j, i] >= nums[left]
         *
         * */
        private int partition(int[] nums, int left, int right) {
            int pivot = left;
            int index = left + 1;
            for (int i = index; i <= right; i++) {
                if (nums[i] < nums[pivot]) {
                    // 小于 pivot 的元素都被交换到前面
                    index++;
                    swap(nums, index, i);
                }
            }
            // 这里因为上面的index++在前面，所以就index
            swap(nums, index, pivot);
            return index;
        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }

    // Solution2： 全部快排然后找第K个
    static class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            int left = 0;
            int right = nums.length - 1;
            quickSort(nums, left, right);
            return nums[k];
        }

        private void quickSort(int[] nums, int left, int right) {
            if (left < right) {
                int partitionIndex = partition(nums, left, right);
                quickSort(nums, left, partitionIndex - 1);
                quickSort(nums, partitionIndex + 1, right);
            }
        }

        private int partition(int[] nums, int left, int right) {
            int pivot = left;
            int index = left + 1;
            for (int i = index; i <= right; i++) {
                // 倒序
                if (nums[i] > nums[pivot]) {
                    swap(nums, i, index);
                    index++;
                }
            }
            swap(nums, index - 1, pivot);
            return index - 1;
        }

        private void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }

    // Solution1: 使用自带的sort方法
    static class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }
            list.sort(Comparator.reverseOrder());

            return list.get(k);

        }
    }

}
