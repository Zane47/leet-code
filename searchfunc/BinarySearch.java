package leetcode.searchfunc;


import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {

    public static void main(String[] args) {
        ArrayList<Integer> list = createList();
        Collections.sort(list);
        // 有序序列才可以用二分查找
        binarySearch(list, 20);
    }

    private static void binarySearch(ArrayList<Integer> list, Integer target) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < list.get(mid)) {
                right = mid - 1;
            }
            else if (target > list.get(mid)) {
                left = mid + 1;
            }
            else {
                System.out.println("find it!" + target + "at position: " + mid);
                return;
            }
        }
        System.out.println("not find! " + target);


    }

    private static ArrayList<Integer> createList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(18);
        list.add(7);
        list.add(96);
        list.add(47);
        list.add(52);
        list.add(24);
        list.add(21);
        list.add(30);

        return list;
    }


}
