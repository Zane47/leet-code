package leetcode.twopoints;


/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 */

public class ReverseString2_541 {


    public static void main(String[] args) {

        String s = "abcdefg";
        int k = 2;
        System.out.println(new Solution().reverseStr(s, k));

    }


    static class Solution {
        public String reverseStr(String s, int k) {
            int length = s.length();
            char[] charArray = s.toCharArray();
            for (int i = 0; i < s.length(); i = 2*k + i) {
                if (length - i < k) {
                    reverse(charArray, i, k - 1);
                }

                reverse(charArray, i, );

            }


            return String.valueOf(charArray);
        }

        /**
         * 翻转
         * @param charArray
         * @param left
         * @param right
         */
        private void reverse(char[] charArray, int left, int right) {
            while (left < right) {
                char temp = charArray[left];
                charArray[right] = charArray[left];
                charArray[left] = temp;
                left++;
                right--;
            }
        }

    }




}
