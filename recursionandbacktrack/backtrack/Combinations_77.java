package leetcode.recursionandbacktrack.backtrack;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combinations_77 {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(new Solution().combine(n, k));
    }

    static class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempPath = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {



            return result;
        }
    }



}
