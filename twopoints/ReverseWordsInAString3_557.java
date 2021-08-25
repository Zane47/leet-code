package leetcode.twopoints;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 */

public class ReverseWordsInAString3_557 {
    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println(new Solution().reverseWords(str));
    }

    /**
     * 原地解法
     *
     * 因此Java中String为final，不可变类型
     * 转成char[]
     *
     */
    static class Solution {
        public String reverseWords(String s) {
            char[] charArray = s.toCharArray();
            return s;
        }
    }

    /**
     * 开辟新空间
     *
     */
    static class Solution1 {
        public String reverseWords(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strArray = s.split(" ");
            for (String str: strArray) {
                char[] charArray = str.toCharArray();
                swapArray(charArray);
                stringBuilder.append(charArray);
                stringBuilder.append(' ');
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }

        private void swapArray(char[] charArray) {
            int v1 = 0;
            int v2 = charArray.length - 1;
            while (v1 < v2) {
                swap(charArray, v1 , v2);
                v1++;
                v2--;
            }
        }

        private void swap(char[] charArray, int v1, int v2) {
            char c = charArray[v1];;
            charArray[v1] = charArray[v2];
            charArray[v2] = c;
        }

    }




}
