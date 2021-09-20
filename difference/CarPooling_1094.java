package leetcode.difference;

/**
 *
 *
 *
 */
public class CarPooling_1094 {

    public static void main(String[] args) {

        int[][] trips = new int[][]{
            {2, 1, 5},
            {3, 3, 7}
        };
        int capacity = 4;
        System.out.println(new Solution().carPooling(trips, capacity));

    }

    /**
     * 差分数组
     */
    static class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            int[] diff = new int[1001];
            for (int i = 0; i < trips.length; i++) {
                diff[trips[i][1]] += trips[i][0];

                // 这里不需要后一位再减，因为这一站就下车
                // [[2,1,5],[3,5,7]]
                // 3
                diff[trips[i][2]] -= trips[i][0];
            }

            int sum = 0;
            for (int i = 0; i <= 1000; i++) {
                sum += diff[i];
                if (sum > capacity) {
                    return false;
                }
            }
            return true;
        }
    }



    /**
     * 直接记录上下车的容量变化情况
     * 起始点和下车点同时存在的客人数
     */
    static class Solution2 {
        public boolean carPooling(int[][] trips, int capacity) {
            // 数据记录变化，每一站的乘客数量
            int[] capacityChanges  = new int[1001];
            for (int i = 0; i < trips.length; i++) {
                capacityChanges[trips[i][1]] += trips[i][0];
                capacityChanges[trips[i][2]] -= trips[i][0];
            }
            int load = 0;
            for (int i = 0; i < capacityChanges.length; i++) {
                load += capacityChanges[i];
                if (load > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 路径上的点同时存在的客人数
     * trips[i] = [num_passengers, start_location, end_location] 包含了第 i 组乘客的行程信息：
     *
     * 必须接送的乘客数量；
     * 乘客的上车地点；
     * 以及乘客的下车地点。
     */
    static class Solution1 {
        public boolean carPooling(int[][] trips, int capacity) {
            int[] personCount = new int[1001];
            for (int i = 0 ;i < trips.length; i++) {
                // 起点到终点，终点的时候就下车了，不用=
                for (int j = trips[i][1]; j < trips[i][2]; j++) {
                    personCount[j] += trips[i][0];
                    if (personCount[j] > capacity) {
                        return false;
                    }
                }
            }
            return true;
        }

    }



}
