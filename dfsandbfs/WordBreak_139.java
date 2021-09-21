package leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.List;


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

    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {

        }
    }




}
