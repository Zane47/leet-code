package leetcode.lcstring;
// 415
public class AddStrings_415 {


    public static void main(String[] args) {
        Solution solution = new Solution();
        // solution.addStrings("1", "9");
        solution.addStrings("456", "77");
    }


    /**
     * 练手再写一次
     */
    static class Solution_2 {
        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int i = num1.length()-1;
            int j = num2.length()-1;

            // 当有一个还没结束的时候
            int temp = 0;
            // 进位
            int carry = 0;
            while (i >= 0 || j >= 0) {

                int n1,n2;
                if (i >= 0) {
                    n1 = num1.charAt(i) - '0';
                } else {
                    n1 = 0;
                }
                if (j >= 0) {
                    n2 = num2.charAt(j) - '0';
                } else {
                    n2 = 0;
                }
                temp = n1 + n2 + carry;
                if (temp >= 10) {
                    carry = 1;
                } else {
                    carry = 0;
                }
                // 个位数
                sb.append(temp % 10);
                i--;
                j--;
            }
            if (carry == 1) {
                sb.append("1");
            }
            return sb.reverse().toString();

        }
    }


    /**
     * 从后往前，列竖式
     * -‘0’来计算数字
     */
    static class Solution {
        public String addStrings(String num1, String num2) {
            StringBuilder result = new StringBuilder();
            int i = num1.length() - 1;
            int j = num2.length() - 1;
            // 进位
            int carry = 0;
            int temp = 0;
            for (; i >= 0 || j >= 0; i--, j--) {
                int n1 = -1;
                int n2 = -1;
                if (i >= 0) {
                    n1 = num1.charAt(i) - '0';
                }
                else {
                    n1 = 0;
                }
                if (j >= 0) {
                    n2 = num2.charAt(j) - '0';
                }
                else {
                    n2 = 0;
                }
                temp = n1 + n2 + carry;
                carry = temp / 10;
                result.append(temp % 10);
            }
            // 如果都走完了还有一个进位，那么就需要把数前面+1进位
            if (carry == 1) {
                result.append(1);
            }
            return result.reverse().toString();

        }
    }

}
