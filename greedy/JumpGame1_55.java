package leetcode.greedy;

public class JumpGame1_55 {
    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1, 1, 4};
//        int[] nums = new int[]{3, 2, 1, 0, 4};
//        int[] nums = new int[]{0, 2, 3};
//        int[] nums = new int[]{1, 0, 1, 0};
//        int[] nums = new int[]{3, 2, 1};
        int[] nums = new int[]{2, 5, 0, 0};
        System.out.println(new Solution().canJump(nums));
    }

    /**
     * i+nums[i]: 第i个位置能跳到的最远位置
     * 那么输出每个位置的能到达的最远距离
     * <p>
     * 这个方法的思路是: 尽可能到达最远位置（贪心）。
     * 最远能到达某个位置，就一定能到达它前面的任何位置。
     * <p>
     * 同时注意, 为了防止1, 0, 1, 0的这种情况,
     * 也就是中间的某个位置无法到达. 到达0后无法再跳跃
     * <p>
     * 如果当前位置能到达 && 当前位置+跳数 > 最远位置，
     *     就更新最远位置
     */
    static class Solution {
        public boolean canJump(int[] nums) {
            int len = nums.length;
            // 只有一个数字, 直接到达了
            if (len == 1) {
                return true;
            }
            // 如果第一个数字是0, 跳不了
            if (nums[0] == 0) {
                return false;
            }


            // ------------------------ do ------------------------
            // 记录最远能到达的index
            int maxIndex = 0;


            // 最后一位不用计算
            // 如果当前位置能到达，并且当前位置+跳数>最远位置，就更新最远位置
            for (int i = 0; i < nums.length - 1; i++) {
                // 能到达 && 当前位置+跳 > 最远距离
                if (maxIndex >= i && maxIndex < i + nums[i]) {
                    maxIndex = i + nums[i];
                }
            }

            return maxIndex >= len - 1;
        }
    }
}
