package leetcode.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://cloud.tencent.com/developer/article/1736172
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * 两个线程交替打印 0~100 的奇偶数
 * 通过 N 个线程顺序循环打印从 0 至 100
 * 多线程按顺序调用，A->B->C，AA 打印 5 次，BB 打印10 次，CC 打印 15 次，重复 10 次
 * 用两个线程，一个输出字母，一个输出数字，交替输出 1A2B3C4D...26Z
 */
public class test1 {

    private int times;
    private int state;
    private Lock lock = new ReentrantLock();

    test1(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times;) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.println(name);
            }
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        test1 test1 = new test1(2);
        new Thread(() -> {
            test1.printLetter("B", 1);
        }, "B").start();

        new Thread(() -> {
            test1.printLetter("A", 0);
        }, "A").start();

        new Thread(() -> {
            test1.printLetter("C", 2);
        }, "C").start();

    }
}
