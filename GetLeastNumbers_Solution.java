package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class GetLeastNumbers_Solution {

    public static void main(String[] args) {
        new GetLeastNumbers_Solution();
    }

    public GetLeastNumbers_Solution() {
        Solution solution = new Solution();
        int[] input = new int[]{7, 9, 5, 6, 3, 2, 1};
        solution.GetLeastNumbers_Solution(input, 4);
    }
    public class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer> result = new ArrayList<>();
            if (k == 0 || k > input.length) {
                return result;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // (x < y) ? -1 : ((x == y) ? 0 : 1);
                    return o2.compareTo(o1);
                    // 正序，从小到大
                    // return o1.compareTo(o2);
                }
            });

            for (int i = 0; i < input.length; i++) {
                if (maxHeap.size() != k) {
                    maxHeap.add(input[i]);
                } else if (maxHeap.peek() > input[i]) {
                    Integer temp = maxHeap.poll();
                    temp = null;
                    maxHeap.add(input[i]);
                }
            }
            for (Integer integer : maxHeap) {
                result.add(integer);
            }
            return result;
            
            
            
            
            

        }


    }



}



