package leetcode.twopoints;

/**
 * https://www.lintcode.com/problem/415/
 *
 */
public class IsPalindrome_LintCode415 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("1b , 1"));
    }

    public static boolean isPalindrome(String s) {
        // write your code here
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }

        s = s.trim();

        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }


            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
