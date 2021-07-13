package leetcode;



public class MinStack155 {

    public static void main(String[] args) {
        /*MinStack obj = new MinStack();
        int val = 5;
        obj.push(val);
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();*/

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   //--> 返回 -3.
        minStack.pop();
        minStack.top();      //--> 返回 0.
        minStack.getMin();   //--> 返回 -2.




    }
}
class MinStack {
    /** initialize your data structure here. */
    MinStack() {



    }

    void push(int val) {
        // 将元素 x 推入栈中。
    }

    void pop() {
        // 删除栈顶的元素。
    }

    int top() {
        // 获取栈顶元素。
        return 0;
    }

    int getMin() {
        // 检索栈中的最小元素。
        return 0;
    }
}