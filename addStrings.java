package LeetCode;
// 415
public class addStrings {


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.addStrings("1", "9");
    }



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
