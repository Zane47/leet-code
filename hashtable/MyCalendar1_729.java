package leetcode.hashtable;

import java.util.Arrays;

/**
 * 线段树? todo
 */
public class MyCalendar1_729 {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(10, 20);
        myCalendar.book(15, 25);
        myCalendar.book(20, 30);
        int a = 0;
    }


    static class MyCalendar {

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            return false;
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */


    /**
     * hashTable, 数组太大
     */
    static class MyCalendar1 {
        private boolean[] hashTable;

        /*public MyCalendar() {
            hashTable = new boolean[200];
            // Arrays.fill(hashTable, false);
        }*/

        public boolean book(int start, int end) {
            boolean[] origin = Arrays.copyOf(hashTable, 200);
            for (int i = start; i < end; i++) {
                if (hashTable[i]) {
                    hashTable = Arrays.copyOf(origin, 200);
                    return false;
                }
                hashTable[i] = true;
            }
            return true;
        }
    }

}
