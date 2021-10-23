package leetcode.math;

/**
 *
 * 求因数和质因数
 *
 *
 */

public class Factor {

    public static void main(String[] args) {

        int num = 14;


        calcFactorsOfNums(num);
        System.out.println();

        calcPrimeFactors(num);
    }

    private static void calcPrimeFactors(int num) {
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num = num / i;
            }
        }

    }

    private static void calcFactorsOfNums(int num) {

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
            }
        }



    }


}
