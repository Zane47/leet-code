package leetcode.simulation;

public class CalculateMoneyInLeetcodeBank_1716 {
    public static void main(String[] args) {
        int n = 18;
        System.out.println(new Solution().totalMoney(n));
    }


    /**
     * 等差数列求和优化
     * <p>
     * 首项 + 末项 乘以 项数 除以 2
     */
    static class Solution {
        public int totalMoney(int n) {
            // 计算完整周的金钱
            // 完整的周
            int fullWeekNum = n / 7;
            int result1 = (28 + 28 + 7 * (fullWeekNum-1)) * fullWeekNum / 2;

            // 零散周的金钱
            // 最后一行剩余的元素个数
            int colLeft = n % 7;
            int result2 = (fullWeekNum + 1 + fullWeekNum + 1 + colLeft - 1) * colLeft / 2;

            return result1 + result2;
        }
    }


    /**
     * 创建二维数组即可,
     * n/7行, 7列
     * 每一行都比上一行的和大7
     */
    static class Solution1 {
        public int totalMoney(int n) {
            int col = 7;
            // 存了几周的钱
            int row = n / 7 + 1;

            int[][] nums = new int[row][col];
            for (int i = 0; i < row; i++) {
                int rowStart = i + 1;
                for (int j = 0; j < col; j++) {
                    nums[i][j] = rowStart + j;
                }
            }

            int result = 0;
            // 求和
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (n > 0) {
                        result += nums[i][j];
                        n--;
                    }
                }
            }


            return result;
        }
    }
}
