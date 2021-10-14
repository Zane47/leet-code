package leetcode.sortfunc;


public class test {
    public static void main(String[] args){
        // Solution solution = new P1TwoSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)






}


class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] result = {i ,j};
                    return result;
                }
            }
        }

        return null;
    }
}


