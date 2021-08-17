package leetcode.search.binarysearch;

/**
 * The isBadVersion API is defined in the parent class VersionControl.
 *       boolean isBadVersion(int version);
 */
public class FirstBadVersion_278 {

    public static void main(String[] args) {

        int n = 5;

        System.out.println(new Solution().firstBadVersion(5));


    }

    /**
     * 你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     */


    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 1;
            int right = n;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (isBadVersion(mid)) {
                    // 答案在区间 [left, mid] 中
                    right = mid;
                }
                else {
                    // left mid bad...bad right
                    // 答案在区间 [mid+1, right] 中
                    left = mid + 1;
                }
            }
            // left = right
            return left;
        }
    }








}
