package leetcode.sortfunc.quicksort;

/**
 * 空间复杂度: O(1)
 *
 * 时间复杂度: 最好的情况: O(nlogn), 最差情况: O(n^2)
 *
 * 其中partition中的for遍历就是n, 这个是无法省略的,
 * 区别的点就在于quickSort该方法:
 * 计算partitionIndex, 因为是按照partitionIndex的位置来进行递归向下
 * 如果最差的情况, 每次的partitionIndex就是left+1
 * 如果最好的情况, 每次的partitionindex就是二分
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] number = { 45, 78, 64, 52, 11, 64, 55, 99, 11, 18, 47 };
        // int[] number = {5, 2, 3, 1};
        int left = 0;
        int right = number.length - 1;
        // 正序排列
        quickSort(number, left, right);
        for (int i : number) {
            System.out.println(i);
        }

    }


    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            // 对枢轴的前半部分和后半部分（以枢轴为分界，都无需包括枢轴）快排
            // 左边部分都排玩之后才会递归到右半部分
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        // 当一轮都换完之后，需要把枢轴放到index-1的位置
        // 那么比pivot小的数就都在pivot左边了，比pivot大的数就都在pivot右边了
        swap(arr, pivot, index - 1);
        // 返回枢轴的位置
        return index - 1;
        // 为什么index-1 因为在for循环中我们是先swap再index++的，所以index一直都是后一个
        // 如果我们先index++，在swap，那么就是return index
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
