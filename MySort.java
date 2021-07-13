package leetcode;
/*
* 给定一个数组，请你编写一个函数，返回该数组排序后的形式。
[5,2,3,1,4]
[1,2,3,4,5]

[5,1,6,2,5]
[1,2,5,5,6]

 * */

public class MySort {

    public static void main(String[] args) {
        new MySort();
    }

    private MySort() {
        int[] arr = {5, 2, 3, 1, 4};
        int[] arr2 = {5, 1, 6, 2, 5};
        new Solution().MySort(arr);
    }


    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         * 将给定数组排序
         * @param arr int整型一维数组 待排序的数组
         * @return int整型一维数组
         */
        public int[] MySort (int[] arr) {
            // write code here
            // 快排




            return null;
        }
    }

}
