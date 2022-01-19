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
 */
public class Combinations_77 {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(new Solution().combine(n, k));
    }

    /**
     * 要解决 n为100，k为50的情况，暴力写法需要嵌套50层for循环，那么回溯法就用递归来解决嵌套层数的问题。
     *
     * 递归来做层叠嵌套（可以理解是开k层for循环），每一次的递归中嵌套一个for循环，那么递归就可以用于解决多层嵌套循环的问题了。
     *
     * 用树状结构来理解组合的回溯
     * 可以看出这个棵树，一开始集合是 1，2，3，4， 从左向右取数，取过的数，不在重复取。
     *
     * 第一次取1，集合变为2，3，4 ，因为k为2，我们只需要再取一个数就可以了，分别取2，3，4，得到集合[1,2] [1,3] [1,4]，以此类推。
     *
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围。
     *
     * 图中可以发现n相当于树的宽度，k相当于树的深度。
     *
     * 图中每次搜索到了叶子节点，我们就找到了一个结果。 相当于只需要把达到叶子节点的结果收集起来，就可以求得 n个数中k个数的组合集合。
     *
     * 作者：carlsun-2
     * ！！！链接：https://leetcode-cn.com/problems/combinations/solution/dai-ma-sui-xiang-lu-dai-ni-xue-tou-hui-s-0uql/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempPath = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {


            // start这个参数用来记录本层递归的中，集合从哪里开始遍历
            // 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex。
            // 因为本身就是有序的，从1到n，所以这里可以使用startIndex来收缩范围
            // 例如：在集合[1,2,3,4]取1之后，下一层递归，就要在[2,3,4]中取数了
            // 按照题意从1开始
            backTrack(n, k, 1);

            return result;
        }

        /**
         * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠start
         * @param n
         * @param k
         * @param start 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
         */
        private void backTrack(int n, int k, int start) {
            // 回溯的终止条件：
            // 到达叶子节点，path这个数组的大小如果达到k，说明我们找到了一个子集大小为k的组合了，
            // path存的就是根节点到叶子节点的路径。
            if (tempPath.size() == k) {
               result.add(new ArrayList<>(tempPath));
               return;
            }
            for (int i = start; i <= n ; i++) {
                tempPath.add(i);
                //System.out.println(tempPath);
                backTrack(n, k, i + 1);
                tempPath.remove(tempPath.size() - 1);
                //System.out.println(tempPath);
            }
        }
    }



}
