package leetcode.dp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 杨辉三角
 */
public class PascalsTriangle_118 {
    public static void main(String[] args) {
        int numRows = 5;

        System.out.println(new Solution().generate(numRows));
    }


    /**
     * 模拟解法
     */
    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();

            // 初始化第一行
            List<Integer> init = new ArrayList<>();
            init.add(1);
            result.add(init);
            if (numRows == 1) {
                return result;
            }
            init = new ArrayList<>();
            init.add(1);
            init.add(1);
            result.add(init);
            if (numRows == 2) {
                return result;
            }

            for (int i = 3; i <= numRows; i++) {
                int[] nums = new int[i];
                // 第一位和最后位是1
                nums[0] = 1;
                nums[i - 1] = 1;

                // 第i行的[1, i-1]位
                for (int j = 1; j <= i - 2; j++) {
                    nums[j] = result.get(i - 2).get(j - 1) + result.get(i - 2).get(j);
                }

                List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
                result.add(collect);
            }


            return result;
        }
    }
}
