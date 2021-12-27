package leetcode.greedy;

/**
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 */
public class JumpGame2_45 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
//        int[] nums = new int[]{2, 3, 0, 1, 4};

        System.out.println(new Solution().jump(nums));
    }

    /**
     * 贪心算法, 找局部最优解
     * 每次跳的时候, 都去看我跳的范围是什么, 然后在跳的范围中, 去找下一次我能跳的最远的
     * <p>
     * 2 3 1 1 4
     * 一开始在位置0(数字2)的时候, 我能跳到位置1(数字3)和位置2(数字1), 然后数字3可以跳的更远,
     * 所以我就到数字3(位置1)去
     * 到了数字3后, 我可以跳的范围是位置2, 位置3, 位置4, 位置4就是目的地
     */
    static class Solution {
        public int jump(int[] nums) {
            // 跳的次数
            int step = 0;

            // 最右边, 这次能跳的最远的距离
            int end = 0;

            // 最远距离
            int max = 0;

            // 最后一位不用考虑
            for (int i = 0; i < nums.length - 1; i++) {
                // i+nums[i]: 能跳到的最大距离
                // 获取下次起跳的最远距离是多少 在当前范围内的
                max = Math.max(max, i + nums[i]);

                if (end == i) {

                }

            }


            return step;
        }
    }
}
