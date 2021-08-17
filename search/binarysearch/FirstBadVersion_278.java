package leetcode.search.binarysearch;

/**
 * The isBadVersion API is defined in the parent class VersionControl.
 *       boolean isBadVersion(int version);
 */

// 注意两种不同的等号取值方法

/**
 *
 * 有关while条件是否取等号的问题（感觉还不是完全理解）有点迷茫什么是否取等号什么时候不取等号了
 *
 * 不取等号，循环结束条件是 low == high 由于low == high，因此返回哪一个都可以，因为本题应该默认一定有错误的版本 因此直接返回 low 或者 high 即可，但是在 二分查找某个数值时，不写等号还要判断 low位置的元素是否等于目标数
 *
 * 取等号(之前使用二分查找时是根据区间是左闭右闭或左闭右开来判断是否要取等号的) 在这里说白了就是二分查找求左边界（收缩右边界即可）
 *
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


    class Solution1 extends VersionControl {
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


    class Solution2 extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 1;
            int right = n;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (isBadVersion(mid)) {
                    // 答案在区间 [left, mid] 中
                    right = mid - 1;
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
