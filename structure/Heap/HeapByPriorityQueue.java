package leetcode.structure.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapByPriorityQueue {
    public static void main(String[] args) {

        // 大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o2.compareTo(o1);
            }

        });

        maxHeap.add(5);
        maxHeap.add(4);
        maxHeap.add(9);
        maxHeap.add(6);

        maxHeap.peek();
        maxHeap.poll();
        int a = 0;
    }
}
