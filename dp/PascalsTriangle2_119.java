package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle2_119 {
    public static void main(String[] args) {
        int rowIndex = 5;

        System.out.println(new Solution1().getRow(rowIndex));
    }



    static class Solution1 {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i <= rowIndex; i++) {
                List<Integer> origin = new ArrayList<>(row);

                row.add(1);
                for (int j = 1; j < i; j++) {
                    row.set(j, origin.get(j) + origin.get(j - 1));
                }
            }

            return row;
        }

    }


    /**
     * 递推
     */
    static class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < rowIndex + 1; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                    }
                }
                ret.add(row);
            }

            return ret.get(rowIndex);
        }
    }
}
