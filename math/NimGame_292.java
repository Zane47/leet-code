package leetcode.math;

/**
 * 每次可以拿1-3块石头
 * 我先手
 * 拿到最后一块石头就算赢
 *
 * 直接数学模拟,
 * 两个人博弈, 凑4,
 * 1. 如果是4的整数倍, 那么我先手, 对手就每次都凑四就可以了, 那么我就输了
 * 2. 如果不是4的整数倍, 那么我先手拿掉%4的结果, 剩下的石头就是4的倍数, 然后对面先手, 我凑四, 我赢
 * 所以就是看这个数字是不是4的整数倍
 */
public class NimGame_292 {
    public static void main(String[] args) {
        System.out.println(new Solution().canWinNim(26));
    }


    static class Solution {
        public boolean canWinNim(int n) {
            if (n < 4) {
                return true;
            }
            if (n % 4 == 0) {
                return false;
            } else {
                return true;
            }
        }
    }
}
