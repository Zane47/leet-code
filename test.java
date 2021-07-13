package leetcode;

import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        int x = 3;
        int y = 4;

        int result[] = swap(x, y);

        System.out.println(result[0]);
        System.out.println(result[1]);


        HashMap<Integer, Integer> map = new HashMap<>();


    }

    private static int[] swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
        int result[] = {x, y};
        return result;
    }


}
