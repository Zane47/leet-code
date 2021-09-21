package leetcode.search.binarysearch;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 *
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，
 * 如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class SearchInRotatedSortedArray_33 {
    public static void main(String[] args) {

        int[] nums = new int[]{4, 5, 6, 7, 8, 0, 1, 2};
        int target = 8;

        System.out.println(new Solution().search(nums, target));
    }


    /**
     * 数组一分为二，一定有一边是有序的，例如45678012，分成4567 8012
     * 那么另外
     * 1. 首先明白，旋转数组后，从中间划分，一定有一边是有序的。
     * 2. 由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
     * 3. 这题找目标值，遇到目标值即返回
     * 4. 注意：
     * 由于有序的一边的边界值可能等于目标值，
     * 所以判断目标值是否在有序的那边时应该加个等号
     * (在二分查找某个具体值得时候如果把握不好边界值，可以再每次查找前判断下边界值，
     * 也就是while循环里面的两个if注释)
     */
    static class Solution {
        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            int left = 0;
            int right = n - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] == target) {
                    return mid;
                }
                // 左右肯定至少有一边是有序的
                // 注意这里的等号不能省略
                if (nums[mid] >= nums[right]) {
                    // 左边有序
                    if (nums[mid] > target && nums[left] <= target) {
                        // target在左边
                        right = mid - 1;
                    } else {
                        // target在右边
                        left = mid + 1;
                    }
                } else if (nums[mid] < nums[right]) {
                    // 右边有序
                    if (target > nums[mid] && target <= nums[right]) {
                        // target在右边
                        left = mid + 1;
                    } else {
                        // target在左边
                        right = mid - 1;
                    }
                }
            }
            return -1;

        }
    }

}
