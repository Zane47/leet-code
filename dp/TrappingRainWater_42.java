package leetcode.dp;

public class TrappingRainWater_42 {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Solution().trap(height));
    }


    /**
     * 提前存储每个位置上所有左边柱子高度的最大值和右边柱子高度的最大值
     *
     * 时间复杂度: O(n)
     * 空间复杂度; O(n)
     */
    static class Solution {
        public int trap(int[] height) {
            int result = 0;

            int length = height.length;

            int[] left_max_array = new int[length];
            int[] right_max_array = new int[length];

            int leftMax = height[0];
            for (int i = 0; i < length; i++) {
                if (height[i] > leftMax) {
                    leftMax = height[i];
                }
                left_max_array[i] = leftMax;
            }

            int rightMax = height[length - 1];
            for (int i = length - 1; i >= 0; i--) {
                if (height[i] > rightMax) {
                    rightMax = height[i];
                }
                right_max_array[i] = rightMax;
            }

            for (int i = 0; i < length; i++) {
                result += Math.min(left_max_array[i], right_max_array[i]) - height[i];
            }

            return result;
        }
    }

    /**
     * 直观的方法
     * 是从左到右遍历height:
     * <p>
     * * 初始化leftMax=0, rightMax=0.
     * * 从[0, index]位置找当前位置左侧的最大值leftMax
     * * 从[index, length-1]位置找当前位置右侧的最大值rightMax
     * * result += min(leftMax, rightMax) - height[index]
     */
    static class Solution1 {
        public int trap(int[] height) {
            int result = 0;

            for (int index = 0; index < height.length; index++) {
                int leftMax = 0;
                int rightMax = 0;

                // 左侧最大值
                for (int j = 0; j <= index; j++) {
                    leftMax = Math.max(leftMax, height[j]);
                }

                // 右侧最大值
                for (int j = index; j <= height.length - 1; j++) {
                    rightMax = Math.max(rightMax, height[j]);
                }

                result += Math.min(leftMax, rightMax) - height[index];
            }

            return result;
        }
    }
}
