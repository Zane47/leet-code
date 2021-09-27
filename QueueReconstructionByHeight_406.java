package leetcode;

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

    static class Solution {
        public int[][] reconstructQueue(int[][] people) {



            return people;
        }
    }



}
