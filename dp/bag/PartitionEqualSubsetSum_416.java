package leetcode.dp.bag;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class PartitionEqualSubsetSum_416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(new Solution().canPartition(nums));
    }

    static class Solution {
        public boolean canPartition(int[] nums) {
            return false;
        }
    }


}
