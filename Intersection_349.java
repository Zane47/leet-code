package leetcode;


import java.util.Arrays;
import java.util.HashSet;

// 两个数组的交集349
public class Intersection_349 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        Solution solution = new Solution();
        solution.intersection(nums1, nums2);
    }

    // 3.排序 + 双指针
    // 先把两个数组都排序，然后i，j指针
    static class Solution3 {
        // 两个数组交集
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> temp = new HashSet<>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;


            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    temp.add(nums1[i]);
                    i++;
                    j++;
                }
                else if (nums1[i] < nums2[j]) {
                    i++;
                }
                else if (nums1[i] > nums2[j]) {
                    j++;
                }
            }

            int[] res = new int[temp.size()];
            int v1 = 0;
            for (Integer index : temp) {
                res[v1] = index;
                v1++;
            }
            return res;
        }
    }

    // 2. 两个set
    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> set1 = new HashSet<>();
            HashSet<Integer> set2 = new HashSet<>();
            for (int i : nums1) {
                set1.add(i);
            }
            // 第二个set添加的时候查看是否contains
            for (int j : nums2) {
                if (set1.contains(j)) {
                    set2.add(j);
                }

            }
            int[] res = new int[set2.size()];
            int v1 = 0;
            for (Integer index : set2) {
                res[v1] = index;
                v1++;
            }
            return res;
        }
    }


    // 法一：暴力比较
    // nums1中所有数字在nums2中做查找
    // 注意重复数字的问题，用hashSet即可
    static class Solution1 {
        // 两个数组交集
        public int[] intersection(int[] nums1, int[] nums2) {
            // 重复数字的处理
            HashSet<Integer> tempResult = new HashSet<>();
            // ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums1[i] == nums2[j]) {
                        tempResult.add(nums1[i]);
                    }
                }
            }

            int[] res = new int[tempResult.size()];
            int v1 = 0;
            for (Integer index : tempResult) {
                res[v1] = index;
                v1++;
            }
            return res;
        }
    }



}
