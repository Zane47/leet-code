package leetcode.sortfunc.quicksort;

public class QuickSort3 {


    public static void main(String[] args) {
        int[] arr = new int[] { 45, 78, 64, 52, 11, 64, 55, 99, 11, 18, 47 };
        quickSort(arr, 0, arr.length - 1);

        for (int value : arr) {
            System.out.println(value);
        }

    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int indexPartition = partition(arr, left, right);
            quickSort(arr, 0, indexPartition - 1);
            quickSort(arr, indexPartition + 1, right);
        }

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
            }
        }





        return 0;
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }


}
