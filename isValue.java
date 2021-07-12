/*
package LeetCode;
import java.util.*;

public class isValue {


    public static void main(String[] args) {
        Solution v1 = new Solution();
        v1.isValid("[]}");


    }

    public static class Solution {
        */
/**
         *
         * @param s string字符串
         * @return bool布尔型
         *//*

        public boolean isValid (String s) {
            // write code here
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(')');
                }
                else if (s.charAt(i) == '[') {
                    stack.push(']');
                }
                else if (s.charAt(i) == '{') {
                    stack.push('}');
                }
                else if (s.charAt(i) != stack.peek()) {
                    return false;
                }
                else if (stack.isEmpty()) {
                    return false;
                }
                else {
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }

}


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack1 = new Stack<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        stack.push(currentNode);
        TreeNode temp = null;
        while (stack.isEmpty()) {
            temp = stack.pop();
            if (temp == null) {
                continue;
            }
            result.add(temp.val);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return result;

    }






public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
        if(cur != null){
        stack.push(cur);
        cur = cur.left; // left
        }else{
        cur = stack.pop();
        res.add(cur.val); // root
        cur = cur.right; // right
        }
        }
        return res;
        }

        作者：dian-dao-de-hu-die
        链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-qian-zhong-hou-xu-die-dai-bia-ozsw/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



// 前序遍历顺序为：根 -> 左 -> 右
// 后序遍历顺序为：左 -> 右 -> 根
// 所以, 我们可以把前序遍历的稍作修改: 根 -> 右 -> 左,
// 然后结果存放到栈里进行倒序, 之后再遍历结果栈就可以输出后序遍历了
public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !s.isEmpty()){
        if(cur != null){
        resStack.push(cur); // root
        s.push(cur);
        cur = cur.right; // right
        }else{
        cur = s.pop();
        cur = cur.left; // left
        }
        }

        List<Integer> res = new ArrayList<>();
        while(!resStack.isEmpty()){
        res.add(resStack.pop().val);
        }
        return res;
        }

        作者：dian-dao-de-hu-die
        链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-qian-zhong-hou-xu-die-dai-bia-ozsw/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



*/
