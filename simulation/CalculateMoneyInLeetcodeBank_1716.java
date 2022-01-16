package leetcode.simulation;

public class CalculateMoneyInLeetcodeBank_1716 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution().totalMoney(n));
    }

    /**
     * 创建二维数组即可,
     * n/7行, 7列
     */
    static class Solution {
        public int totalMoney(int n) {
            int col = 7;
            int row = n / 7 + 1;

            int[][] nums = new int[row][col];
            for (int i = 0; i < row; i++) {
                int rowStart = i + 1;
                for (int j = 0; j < col; j++) {
                    nums[i][j] = rowStart + j;
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    
                }
                
            }
            return 0;
        }
    }
}
