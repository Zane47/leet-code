package leetcode.sortfunc.quicksort;




public class QuickSort {
    private static int count = 0;

    public static void main(String[] args) {
        int[] number = {45, 78, 64, 52, 11, 64, 55, 99, 11, 18, 47};
        int left = 0;
        int right = number.length - 1;
        quickSort(number, left, right);
        for (int i : number) {
            System.out.println(i);
        }

    }

    private static void quickSort(int[] number, int left, int right) {
        // left >= right，只有一个元素，返回
        if (left >= right) {
            return;
        }
        int pivot = number[left];
        int i = left;
        int j = right;

        while (i < j) {
            // j向左移，直到遇到比pivoty小的值
            while (number[j] >= pivot && i < j) {
                j--;
            }
            // i向右移，直到遇到比pivot大的值
            while (number[i] <= pivot && i < j) {
                i++;
            }

            // i和j指向的元素交换
            if(i < j){
                int temp=number[i];
                number[i]=number[j];
                number[j]=temp;
            }
        }
        number[left]=number[i];
        number[i]=pivot;
        count++;
        quickSort(number, left,i - 1);
        quickSort(number,i + 1, right);
    }
}







