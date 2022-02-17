package leetcode.structure.heap;

/**
 * 最大堆和最小堆是二叉堆的两种形式。
 * 最大堆：根结点的键值是所有堆结点键值中最大者，且每个结点的值都比其孩子的值大。
 * 最小堆：根结点的键值是所有堆结点键值中最小者，且每个结点的值都比其孩子的值小。
 *
 * 最大堆的父元素与子元素的索引有如下关系
 * 在最大堆中，设根节点索引从1开始，当父节点索引为i时，左孩子节点2*i , 右孩子索引2*i+1。
 */
public class HandWritingHeap {


    public static void main(String[] args) {

        MaxHeap heap = new MaxHeap(6);
        heap.insert(2);
        heap.insert(6);
        heap.insert(4);
        heap.insert(9);
        heap.insert(8);

        heap.deleteMax();
        heap.deleteMax();
        heap.deleteMax();



    }

    static class MaxHeap {
        private int[] data;     //存放堆数据的数组
        private int size;       //当前堆的大小
        private int capacity;   //堆的最大容量

        public MaxHeap(int maxSize) {
            // 0的位置不放数据
            this.data = new int[maxSize + 1];
            this.size = 0;
            this.capacity = maxSize;
        }

        public void insert(int val) {
            if (this.size == capacity) {
                System.out.println("fail:堆满");
                return;
            }
            // 0位置不放数据
            data[++size] = val;
            size++;

            // 插入在最后的元素上移方法
            shiftUp(size);
        }

        private void shiftUp(int index) {
            // 数组可能越界问题
            // 当此元素比父元素大时，交换这两个元素位置
            while (index > 1 && data[index] > data[index / 2]) {
                int temp = data[index];
                data[index] = data[index / 2];
                data[index / 2] = temp;
                index /= 2;
            }
        }

        //删除堆的最大元素
        // 将最后一个元素放到第一个元素位置（替换最大元素），
        // 然后将这个元素不断下移到恰当位置(shiftDown)
        public int deleteMax() {
            if (this.size == 0) {
                // 堆空
                System.out.println("fail: 堆空");
            }
            int temp = data[1];
            // 将最后一个元素放到第一个元素位置
            data[1] = data[this.size];
            this.size--;

            // 然后将第一个元素下移到适当的位置
            shiftDown(1);
            return temp;
        }

        private void shiftDown(int index) {
            while (2 * index <= this.size) {
                // 将要将data[i]与data[j]交换
                int j = 2 * index;
                // 让j指向他的孩子结点中的大的那一个
                if(j + 1 <= size && data[j] < data[j+1]){
                    j += 1;
                }
                if (data[index] > data[j]) {
                    break;
                }
                //元素下移
                int temp = data[index];
                data[index] = data[j];
                data[j] = temp;
                index = j;
            }


        }


        public void heapSort(int arr[], MaxHeap heap){
            for(int i = 0; i < arr.length; i++){
                heap.insert(arr[i]);
            }
            for(int i = arr.length-1; i >=0 ; i--){
                arr[i] = heap.deleteMax();
            }
        }



    }



}
