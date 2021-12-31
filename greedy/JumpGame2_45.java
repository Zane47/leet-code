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
//        int[] nums = new int[]{0};

        System.out.println(new Solution().jump(nums));
    }


    /**
     * 从上面代码观察发现，其实被 while 包含的 for 循环中，i 是从头跑到尾的。
     * 只需要在一次 跳跃 完成时，更新下一次 能跳到最远的距离。
     * 并以此刻作为时机来更新 跳跃 次数。
     * 就可以在一次 for 循环中处理。
     */
    static class Solution {
        public int jump(int[] nums) {
            int step = 0;
            int end = 0;
            int maxPos = 0;

            for (int i = 0; i < nums.length - 1; i++) {
                // 找能跳的最远的
                maxPos = Math.max(nums[i] + i, maxPos);
                // 遇到边界，就更新边界，并且步数加一
                if (i == end) {
                    end = maxPos;
                    step++;
                }
            }
            return step;
        }
    }

    /**
     * 贪心算法, 找局部最优解
     * 每次跳的时候, 都去看我跳的范围是什么, 然后在跳的范围中, 去找下一次我能跳的最远的
     * <p>
     * 2 3 1 1 4
     * 一开始在位置0(数字2)的时候, 我能跳到位置1(数字3)和位置2(数字1), 然后数字3可以跳的更远,
     * 所以我就到数字3(位置1)去
     * 到了数字3后, 我可以跳的范围是位置2, 位置3, 位置4, 位置4就是目的地
     * <p>
     * i+nums[i]: 能跳的最远距离
     * 这里我就一直跳, 直到超过了边界
     * <p>
     * <p>
     * -> 优化Solution2
     */
    static class Solution0 {
        public int jump(int[] nums) {
            int len = nums.length;

            if (len == 1) {
                return 0;
            }

            // 跳的次数
            int step = 0;

            // 最远距离(下标), 初始化: 0, 一开始就自己
            // 这里的初始化是重点
            int maxEnd = 0;

            int start = 0;

            // 最大值的下标
            int maxIndex = 0;

            while (maxEnd < len - 1) {
                // 找到这次范围内能跳到的最远距离
                int maxJumpEnd = 0;
                for (int i = start; i <= maxEnd; i++) {
                    // 找第一个最大值
                    if (i + nums[i] > maxJumpEnd) {
                        maxIndex = i;
                        maxJumpEnd = i + nums[i];
                    }
                }

                // 更新下一次跳跃的起点和终点
                start = maxIndex;
                maxEnd = maxJumpEnd;

                step++;
            }
            return step;
        }

    }
}
