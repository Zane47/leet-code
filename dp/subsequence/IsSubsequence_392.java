package leetcode.dp.subsequence;

public class IsSubsequence_392 {
    public static void main(String[] args) {


    }

    /**
     * 双指针
     */
    static class Solution {
        public boolean isSubsequence(String s, String t) {
            int vs = 0;
            int vt = 0;

            if (s.length() > t.length()) {
                return false;
            }

            while (vs < s.length() && vt < t.length()) {
                if (s.charAt(vs) == t.charAt(vt)) {
                    vs++;
                    vt++;
                } else {
                    vt++;
                }
            }

            return vs == s.length();
        }
    }

}
