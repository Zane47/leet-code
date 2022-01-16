package leetcode.structure.queue;

/**
 * 循环队列
 * 不需要在删除和添加元素之后调整数组
 * 而是使用headIndex和tailIndex来进行存储
 * 同时因为存储了headIndex和count, 所以tailIndex就可以计算出来, 不需要额外存储
 * tailIndex = (headIndex + numsCount - 1) % capacity;
 */
public class DesignCircularQueue_622 {
    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        // 设置长度为 3
        circularQueue.enQueue(1);
        // 返回 true
        circularQueue.enQueue(2);
        // 返回 true
        circularQueue.enQueue(3);
        // 返回 true
        circularQueue.enQueue(4);
        // 返回 false，队列已满
        circularQueue.Rear();
        // 返回 3
        circularQueue.isFull();
        // 返回 true
        circularQueue.deQueue();
        // 返回 true
        circularQueue.enQueue(4);
        // 返回 true
        circularQueue.Rear();
        // 返回 4
    }

    /**
     * 数组方法
     */
    static class MyCircularQueue {
        // 最大容量
        private int capacity;

        // 元素存储
        private int[] nums;

        // 元素个数
        private int numsCount;

        // current index, 现在的容量
        private int index;

        // 头指针, (headIndex + count - 1) mod capacity = tailIndex
        private int headIndex;


        public MyCircularQueue(int k) {
            this.capacity = k;
            nums = new int[k];
            numsCount = 0;
            index = 0;
            headIndex = 0;
        }

        /**
         * 向循环队列插入一个元素。如果成功插入则返回真。
         */
        public boolean enQueue(int value) {
            if (this.capacity == this.numsCount) {
                return false;
            } else {
                this.numsCount++;
                int tailIndex = (headIndex + numsCount - 1) % capacity;
                this.nums[tailIndex] = value;
                return true;
            }
        }

        /**
         * 从循环队列中删除一个元素。如果成功删除则返回真。
         */
        public boolean deQueue() {
            if (this.numsCount == 0) {
                return false;
            } else {
                this.headIndex = (this.headIndex + 1) % this.capacity;
                this.numsCount--;
                return true;
            }
        }

        /**
         * 从队首获取元素。如果队列为空，返回 -1 。
         */
        public int Front() {
            if (this.numsCount == 0) {
                return -1;
            } else {
                return this.nums[this.headIndex];
            }
        }

        /**
         * 获取队尾元素。如果队列为空，返回 -1 。
         */
        public int Rear() {
            if (this.numsCount == 0) {
                return -1;
            } else {
                int tailIndex = (headIndex + numsCount - 1) % capacity;
                return this.nums[tailIndex];
            }
        }

        /**
         * 检查循环队列是否为空。
         */
        public boolean isEmpty() {
            return this.numsCount == 0;
        }

        /**
         * 检查循环队列是否已满。
         */
        public boolean isFull() {
            return this.numsCount == this.capacity;
        }
    }

}
