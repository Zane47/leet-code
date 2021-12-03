package leetcode.concurrency;


/**todo: 不加也一样?
 * 两个线程, 交替打印0-100的数字一
 * 个线程只打印奇数，一个线程只打印偶数，而且要按照顺序打印
 * 偶线程：0
 * 奇线程：1
 * 偶线程：2
 * 奇线程：3
 */
public class AlternatePrintingBy2Threads {
    // solution1
    static final Object lock = new Object();
    // 如果为局部变量: Variable used in lambda expression should be final or effectively final
    static int count = 0;




    public static void main(String[] args) {

        // 使用synchronized关键字
        solution1();




    }

    /**
     * 使用synchronized关键字
     * 创建两个线程，一个线程处理偶数，一个线程处理奇数
     * 两个线程之间通过synchronized进行同步，保证count++每次只有一个线程进行操作
     *
     * 为什么两个线程能交替执行，这里很巧的是count从0123自增过程就是一个奇偶数交替的过程，
     * 实际上两个线程都是在不停的尝试（while循环）进入synchronized代码块，
     * 如果满足相对应的条件（偶数或是奇数）就打印输出。
     *
     */
    private static void solution1() {

        // 0-100计数器

        new Thread(() -> {
            while (count <= 100) {
                /*synchronized (lock)*/ {
                    if ((count % 2) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + count);
                        count++;
                    }
                }

            }

        }, "thread1").start();


        new Thread(() -> {
            while (count <= 100) {
                /*synchronized (lock)*/ {
                    if ((count % 2) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count);
                        count++;
                    }
                }

            }
        }, "thread2").start();




    }


}
