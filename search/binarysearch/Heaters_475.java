package leetcode.search.binarysearch;

import java.util.Arrays;

public class Heaters_475 {

    public static void main(String[] args) {

        int[] houses = new int[]{1,2,3,4};
        int[] heaters = new int[] {1,4};

        System.out.println(new Solution().findRadius(houses, heaters));
    }


    /**
     * 多对多的问题
     * 二分
     * 寻找所有房子与最近供暖器距离 的最大值
     *
     * 此时可想到二分枚举加热半径，
     * 根据在该半径下能加热的房屋数量来判断是否可行，然后移动左右边界使之相同即为答案
     *
     *
     *
     *
     */
    static class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            // 用二分的方法就务必注意要排序
            Arrays.sort(houses);
            Arrays.sort(heaters);





        }
    }

    class Solution {
        // 二分查找
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(heaters);

            int res = 0;

            for (int house : houses) {
                // 二分搜索当前 house 在 heaters 中的位置
                int index = binarySearch(heaters, house);

                if (index < 0) { // 说明没找到，index 等于当前的 house 应该在 heaters 的位置的负数
                    // 计算出当前的 house 应该在 heaters 数组中的位置
                    index = -(index + 1);
                    // 计算当前 house 离左边最近的 heater 的距离
                    int leftDist = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                    // 计算当前 house 离右边最近的 heater 的距离
                    int rightDist = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                    // 当前 house 需要的 heater 的半径取 leftDist 和 rightDist 的最小值
                    res = Math.max(res, Math.min(leftDist, rightDist));
                }
            }

            return res;
        }

        private int binarySearch(int[] heaters, int house) {
            int low = 0;
            int hight = heaters.length - 1;
            while (low <= hight) {
                int mid = (low + hight) >>> 1;

                if (heaters[mid] < house) {
                    low = mid + 1;
                } else if (heaters[mid] > house) {
                    hight = mid - 1;
                } else {
                    return mid;
                }
            }
            return -(low + 1);
        }
    }


    class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            //该法能成立的先决条件为两数组一定都要从小到大排好序
            Arrays.sort(heaters);
            Arrays.sort(houses);
            int l = 0 , r = Integer.MAX_VALUE;
            //开始二分枚举
            while(l < r){
                int mid = (l+r) >>> 1;
                //成立时，右界移动至中心
                if(Helper(houses , heaters , mid)){
                    r = mid;
                    //不成立时，左界移至中心+1
                    //这样可以保证最后的跳出循环l一定为半径最小
                }else{
                    l = mid+1;
                }
            }
            return l;
        }
        public boolean Helper(int[] houses, int[] heaters, int len){
            int m = houses.length , n = heaters.length;
            int index = 0;
            for(int i = 0 ; i < n ; i++){
                long l = heaters[i]-len , r = heaters[i]+len;
                //计算能否完全覆盖房屋
                while(index < m && (long)houses[index] >= l && (long)houses[index] <= r){
                    index++;
                }
                if(index == m) return true;
            }
            return false;
        }
    }


    /**
     * 暴力
     *
     * house到每一个heater的距离，取最小的
     */
    static class Solution0 {
        public int findRadius(int[] houses, int[] heaters) {
            int result = -1;
            for (int i = 0; i < houses.length; i++) {
                int max = Integer.MAX_VALUE;
                for (int j = 0; j < heaters.length; j++) {
                    // 房子距离暖气的距离
                    int dist = Math.abs(houses[i] - heaters[j]);
                    // 这个房子和所有暖气最小的距离
                    max = Math.min(max, dist);
                }
                // 所有房子距离暖气最小距离的最大值，就是结果
                result = Math.max(result, max);
            }

            return result;
        }
    }

}
