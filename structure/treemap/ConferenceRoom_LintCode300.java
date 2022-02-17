package leetcode.structure.treemap;


import java.util.*;

/**
 * https://www.lintcode.com/problem/300
 * https://www.lintcode.com/problem/300/solution/48938
 * 给定一系列会议的开始时间、结束时间[[s1,e1]，[s2,e2]，…(si < ei)及他们的价值。你在同一时刻只能参加一个会议，请问你可以获得的最大价值是多少？
 * <p>
 * 0 <= len(meeting)=len(value) <= 10000
 * 1 <= meeting[i][0] < meeting[i][1] <= 50000
 * value[i] <= 10000
 * <p>
 * 先把start, end, value加到一个collection里 (array, List都行)
 * 然后sort by end time
 * 再用TreeMap依次处理当前的meeting
 */
public class ConferenceRoom_LintCode300 {
    public static void main(String[] args) {
        int[][] meeting = new int[][]{{10, 40}, {20, 50}, {30, 45}, {40, 60}};
        int[] value = new int[]{3, 6, 2, 4};

        System.out.println(new Solution().maxValue(meeting, value));
    }

    static class Solution {
        /**
         * @param meeting: the meetings
         * @param value:   the value
         * @return: calculate the max value
         */
        public int maxValue(int[][] meeting, int[] value) {
            // write your code here
            List<int[]> meetings = new ArrayList<>();
            for (int i = 0; i < value.length; i++) {
                int[] temp = new int[3];
                temp[0] = meeting[i][0];
                temp[1] = meeting[i][1];
                temp[2] = value[i];
                meetings.add(temp);
            }

            Collections.sort(meetings, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] == o2[1]) {
                        return o1[0] - o2[0];
                    } else {
                        return o1[1] - o2[1];
                    }
                }
            });

            int result = 0;
            // key: end time
            // value: curr max value
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int[] meet : meetings) {
                int start = meet[0];
                int end = meet[1];
                int curMaxValue = meet[2];

                // look for the biggest end time < curr start time
                // if exist, then there must not be any conflict between curr & prev meeting
                // so add its value to curr val -> currMaxVal
                Integer key = map.floorKey(start);
                if (key != null) {
                    curMaxValue += map.get(key);
                }

                // look for the biggest end time < curr end time
                // take the bigger value
                key = map.floorKey(end);
                if (key != null) {
                    curMaxValue = Math.max(curMaxValue, map.get(key));
                }
                map.put(end, curMaxValue);
                result = Math.max(result, curMaxValue);
            }
            return result;
        }
    }
}



