package leetcode;

public class MergeTwoLists_21 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        Solution.mergeTwoLists(l1, l2);
    }

    /**
     * 双指针
     */
    static class Solution {
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode node = new ListNode();
            ListNode result = node;
            if (l1 == null) {
                return l2;
            }

            if (l2 == null) {
                return l1;
            }

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                }
                else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }

            if (l1 != null) {
                node.next = l1;
            }
            if (l2 != null) {
                node.next = l2;
            }



            return result.next;
        }
    }

    /**
     * 递归的方式：
     * 终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
     * 返回值：每一层调用都返回排序好的链表头
     * 本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
     * O(m+n)，m 为 l1的长度，n 为 l2 的长度
     *
     *
     * 其实递归就是程序内部维护了一个栈。
     * 这个题就是每次都把最小值压入栈，最后出栈的时候，将所有数连在一起就可以了。
     * 说白了，就是用一个栈维护了顺序。
     * 最后的连接，当然是小的连小的，所以l1 小，就连到 l1,l2 小就连到 l2，
     * 最后先返回的，就是最小的头结点。
     */
    static class Solution1 {
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }

            if (l2 == null) {
                return l1;
            }

            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }



}


class ListNode {
    int val;

    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

