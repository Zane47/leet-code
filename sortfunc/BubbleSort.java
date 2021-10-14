package leetcode.sortfunc;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr={6,3,8,2,9,1};
        System.out.println("排序前数组为：");
        for(int num:arr){
            System.out.print(num+" ");
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        System.out.println("\n排序后数组为：");
        for(int num:arr){
            System.out.print(num+" ");
        }

    }










}
