package leetcode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        new Solution().reconstructQueue(people);
    }

    /**
     * https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-java-xian-p/
     *
     * 倒序排列，高个子先站好位置，矮个子依次插入
     * 结果： [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
     *
     * [7,0]
     * [7,0], [7,1]
     *
     *
     */
    static class Solution {
        public int[][] reconstructQueue(int[][] people) {
            int length = people.length;
            // 先根据第一个倒序，遇到相同的再根据第二个正序
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] > o2[0]) {
                        return o2[0] - o1[0];
                    } else if (o1[0] == o2[1]) {
                        // 重点！！： h相同时按照k的正序
                        return o1[1] - o2[1];
                    }
                    return 0;
                }
            });

            List<int[]> list = new ArrayList<>();

            // todo: ?????
            for (int[] i : people) {
                list.add(i[1], i);
            }

            for (int i = 0; i < length; i++) {

            }

            return people;
        }
    }



}
