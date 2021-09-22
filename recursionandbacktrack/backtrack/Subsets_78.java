package leetcode.recursionandbacktrack.backtrack;

import java.util.*;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 */
public class Subsets_78 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};

        System.out.println(new Solution().subsets(nums));

    }

    /**
     * https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     *
     *
     *
     */
    static class Solution {
        List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            for (int k = 0; k <= nums.length; k++) {
                ArrayList<Integer> tempList = new ArrayList<>();
                backTrack(0, k, nums, tempList);
            }
            return result;
        }

        /**
         * backTrack运行一次可以构造所有长度为k的子集
         *
         * @param start 子集第一个数可以在num中出现的最早位置
         * @param k     当前需要构造子集的长度
         * @param nums
         * @param tempList 正在构造的子集
         */
        private void backTrack(int start, int k, int[] nums, ArrayList<Integer> tempList) {
            if (k == 0) {
                result.add(new ArrayList<>(tempList));
                return;
            }

            for (int i = start; i < nums.length; i++) {
                // 数组中的第i个元素加入子集
                tempList.add(nums[i]);
                // 从i+1位置继续构造当前子集
                backTrack(i + 1, k - 1, nums, tempList);
                // 当前这一步加入的数字从数组中移除，
                // 每一次上一步的backTrack都会移除数字，那么这一步正好移除的是我们加入的数字
                tempList.remove(tempList.size() - 1);
            }
        }


    }




    /**
     * backtrack
     *
     */
    static class Solution01 {
        // 结果
        List<List<Integer>> result = new LinkedList<>();
        // 放过程符合结果的数据
        LinkedList<Integer> tempList = new LinkedList<>();
        public List<List<Integer>> subsets(int[] nums) {
            if (nums.length == 0) {
                // 空集情况
                result.add(new LinkedList<>());
            }
            backTrack(nums, 0);

            return result;
        }

        private void backTrack(int[] nums, int start) {
            // 先把之前的集合加进去
            result.add(new LinkedList<>(tempList));
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[start]);
                backTrack(nums, start+1);
                // 为什么要用LinkedList
                tempList.removeLast();
            }
        }
    }


    /**
     * DFS
     */
    static class Solution00 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();




            return result;
        }
    }

    /**
     * 递归的写法
     * subset([1, 2, 3]) = [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
     * subset(1, 2) = [[], [1], [2], [1,2]]
     * subset(1,2,3) - subset(1,2) = [[3], [1,3], [2,3], [1,2,3]] = subset(1,2)给每个自己加上3
     * A = subset([1,2])
     * subset([1,2,3]) = A + [A[i].add(3) for i = 1..len(A)]
     * 递归
     * 逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
     */
    static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            if (Objects.isNull(nums)) {
                return null;
            }
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            // 单次遍历的结果
            List<Integer> tempList = new ArrayList<>();

            backTrack(nums, result, tempList);


            return result;

        }

        private void backTrack(int[] nums, List<List<Integer>> result, List<Integer> tempList) {




        }
    }

    /**
     * todo: 多看看，想不到这个方法
     * 位运算
     * 假设nums三个数字，每一个都可以选择选或者不选
     * 000 001 010 011 100 101 110 111
     * 2 * 2 * 2
     *
     * 假设nums=[1,2,3,4]，
     * 二进制的0可以写成0000，代表一个数也不取，
     * 1=0001表示去第一个数也就是[1]，
     * 2=0010，表示取第二个数[2]，
     * 3=0011表示取1和2位[1,2]，
     * 4=0100表示[3]....15=1111表示[1,2,3,4]
     */
    static class Solution1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            for (int i = 0; i < Math.pow(2, nums.length); i++) {
                List<Integer> tempList = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    if (((i >> j) & 1) == 1) {
                        tempList.add(nums[i]);
                    }
                }
                result.add(tempList);
            }
            return result;
        }
    }


    /**
     * 逐个枚举
     * 1, 2, 3
     * 比如先加入一个空集让他成为新的子集，然后每遍历一个元素就在原来的子集的后面追加这个值
     * step1: []
     * step2: 访问1，对原来的空集追加1，[] ,[1]
     * step3: 访问2，对原来的每个空集追加2, [], [1], [2], [1,2]
     * step4: 访问3，对原来的每个空寂追加3
     * 0 2 4 8 ，size的增长
     */
    static class Solution0 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            // 空集
            result.add(new ArrayList<>());
            for (int num : nums) {
                int size = result.size();
                // 每遍历一个元素就在之前子集中的每个集合追加这个元素，让他变成新的子集
                for (int i = 0; i < size; i++) {
                    // 遍历之前的子集，封装成一个新的子集，然后再子集中加元素
                    List<Integer> tempList = new ArrayList<>(result.get(i));
                    tempList.add(num);
                    result.add(tempList);
                }
            }
            return result;

        }


    }
}
