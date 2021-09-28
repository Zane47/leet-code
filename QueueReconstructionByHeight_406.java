package leetcode;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 *
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组people所表示的队列。
 * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性
 * （queue[0] 是排在队列前面的人）。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QueueReconstructionByHeight_406 {

    public static void main(String[] args) {

        int[][] people = new int[][] {
            {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
        };

        int[][] people1 = new int[][] {
            {40,11},{81,12},{32,60},{36,39},{76,19},{11,37},{67,13},{45,39},{99,0},{35,20},
            {15,3},{62,13},{90,2},{86,0},{26,13},{68,32},{91,4},{23,24},{73,14},{86,13},{62,6},
            {36,13},{67,9},{39,57},{15,45},{37,26},{12,88},{30,18},{39,60},{77,2},{24,38},{72,7},
            {96,1},{29,47},{92,1},{67,28},{54,44},{46,35},{3,85},{27,9},{82,14},{29,17},{80,11},
            {84,10},{5,59},{82,6},{62,25},{64,29},{88,8},{11,20},{83,0},{94,4},{43,42},{73,9},
            {57,32},{76,24},{14,67},{86,2},{13,47},{93,1},{95,2},{87,8},{8,78},{58,16},{26,75}, {26,15},
            {24,56},{69,9},{42,22},{70,17},{34,48},{26,39},{22,28},{21,8},{51,44},{35,4}, {25,48},
            {78,18},{29,30},{13,63},{68,8},{21,38},{56,20},{84,14},{56,27},{60,40},{98,0}, {63,7},
            {27,46},{70,13},{29,23},{49,6},{5,64},{67,11},{2,31},{59,8},{93,0},{50,39},{84,6}, {19,39}
            // indexOutOfBounds, ArrayList/LinkedList
        };
        System.out.println(Arrays.deepToString(new Solution().reconstructQueue(people1)));

    }

    /**
     * https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-java-xian-p/
     *
     * 倒序排列，高个子先站好位置，矮个子依次插入
     * 结果： [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
     *
     * [7,0]
     * [7,0], [7,1]
     * [7,0], [6,1], [7,1]
     * [5,0], [7,0], [6,1], [7,1]
     * [5,0], [7,0], [5,2], [6,1], [7,1]
     * [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
     *
     * 1.按照height倒序排列，如果相同，则按照k正序排
     * 2。排序后，根据第二位排做插入，如果位置已被占用，那就插入这个位置，其他的元素后移 -> 使用list.add(index, element)
     *
     *
     *
     * 解题思路
     * 先按照身高H从高到低排序，矮的人放在后面，这样在排队时不会对前面已经排好的队伍产生影响（因为数组中记录的是身高大于等于自己的人，身高小于自己的不管放在自己前面和后面都不会有影响）；
     * 如果有身高相同的人，那么就要按照K从小到大排序，因为K小的肯定会排在K大的前面（具体原因可以用反证法来证明：假设有[hi, ki], [hj, kj]，且hi=hj,ki<kj，如果hj在hi的前面，那么ki>=kj+1成立，+1的原因是hi前面有hj。 => 矛盾，假设不成立，hi肯定在hj的前面）。
     * 实现
     * 具体实现分为两步：
     *
     * 第一步：排序
     * 先按照身高H从高到低排序，如果H相同再按照K从小到大排序。
     * 第二步：队列插入
     * 需要满足（前面有k个大于等于自己身高的人）这个条件，也就是插入到第K个位置。
     *
     * 作者：yimeixiaobai
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-ren-h-oynb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // 先根据第一个倒序，遇到相同的再根据第二个正序
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    /*if (o1[0] > o2[0]) {
                        return o2[0] - o1[0];
                    } else if (o1[0] == o2[0]) {
                        // 重点！！： h相同时按照k的正序
                        return o1[1] - o2[1];
                    } else {
                        return o2[0] - o1[0];
                    }*/
                    if (o1[0] != o2[0]) {
                        // 倒序
                        return o2[0] - o1[0];
                    } else {
                        return o1[1] - o2[1];
                    }
                }
            });

            // 直接使用arrayList的插入
            /**ArrayList.add(index, element)
             * Inserts the specified element at the specified position in this list.
             * Shifts the element currently at that position (if any)
             * and any subsequent elements to the right (adds one to their indices).
             */
            /*List<int[]> list = new ArrayList<>();
            for (int[] p : people) {
                // add(index, element)
                list.add(p[1], p);
            }

            int[][] result = new int[people.length][2];
            for (int i = 0; i < people.length; i++) {
                result[i] = list.get(i);
            }
            return result;*/

            // 自己实现插入功能
            int[][] array = new int[1][2];
            for (int[] p : people) {
                // 将index位置的数字更新成改p，其他的后移
                int index = p[1];
                int[] arrayNull = new int[]{0, 0};
                if (index >= array.length) {
                    array = Arrays.copyOf(array, index+1);
                }
                if (!myArrayEquals(arrayNull, array[index])) {
                    // array扩容
                    int size = array.length + 1;
                    array = Arrays.copyOf(array, size);
                    System.arraycopy(array, index, array, index + 1, size-index-1);
                    array[index] = p;
                } else {
                    array[index] = p;
                }
            }
            // 最后一位是null，要去除
            if (array[array.length-1] == null) {
                array = Arrays.copyOf(array, array.length-1);
            }
            return array;
        }

        private boolean myArrayEquals(int[] n1, int[] n2) {
            if (n2 == null) {
                return false;
            }
            for (int i = 0; i < 2; i++) {
                if (n1[i] != n2[i]) {
                    return false;
                }
            }
            return true;
        }
    }



}
