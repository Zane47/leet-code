package leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 *
 *
 *
 */

public class WordBreak_139 {
    public static void main(String[] args) {
        String s = "leetscode";
        List<String> wordDict = new LinkedList<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(new Solution().wordBreak(s, wordDict));
    }



    /**
     * https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
     *
     *
     *
     */
    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] isVisited = new boolean[s.length()];
            queue.offer(0);
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (current == s.length()) {
                    return true;
                }
                // 访问过这个节点了，就跳过
                if (isVisited[current]) {
                    continue;
                }
                // 如果没有访问过，就致成访问过
                isVisited[current] = true;

                // 从current开始向后遍历
                for (int i = current; i <= s.length(); i++) {
                    String substring = s.substring(current, i);
                    if (wordDict.contains(substring)) {
                        queue.offer(i);
                    }
                }
            }


            return false;
        }
    }




}
