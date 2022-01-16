package leetcode.hashtable;

import java.util.*;

/**
 * 平衡树 -> Java中的TreeMap.
 * 其中的floorKey: 如果不存在则返回小于该key的最大key
 * 其中的ceilingKey: 如果不存在则返回大于该key的最小key
 * <p>
 * TreeMap有序map, 按照key的顺序
 */
public class MyCalendar1_729 {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(10, 20);
        myCalendar.book(15, 25);
        myCalendar.book(20, 30);
    }


    static class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
/**
 * Gets the entry corresponding to the specified key; if no such entry
 * exists, returns the entry for the greatest key less than the specified
 * key; if no such entry exists, returns {@code null}.
 */
            Integer prev = calendar.floorKey(start);

/**
 * Gets the entry corresponding to the specified key; if no such entry
 * exists, returns the entry for the least key greater than the specified
 * key; if no such entry exists (i.e., the greatest key in the Tree is less
 * than the specified key), returns {@code null}.
 */
            Integer next = calendar.ceilingKey(start);

            // 这里画数轴来判断大小
            // 前闭后开, 可以取等号
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }


    /**
     * 暴力
     * list存储每一个对, 只要
     */
    static class MyCalendar2 {

        private List<int[]> calendar;

        public MyCalendar2() {
            this.calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] ints : calendar) {
                if (ints[0] < end && ints[1] > start) {
                    return false;
                }
            }
            calendar.add(new int[]{start, end});
            return true;
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

        public MyCalendar1() {
            hashTable = new boolean[200];
            // Arrays.fill(hashTable, false);
        }

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
