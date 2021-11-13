package leetcode.linkedlist;

import java.util.List;

/**todo
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseNodesInKGroup_25 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new Solution().reverseKGroup(head, 2));
    }

    /**
     * https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/k%E4%B8%AA%E4%B8%80%E7%BB%84%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8.md
     */
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode endNode = head;

            for (int i = 0; i < k; i++) {
                endNode = endNode.next;
            }
            // 反转前 k 个元素
            ListNode newHeadNode = reverseKList(head, endNode);
            newHeadNode.next = reverseKGroup(newHeadNode, k);
            return null;
        }

        /**
         * 左闭右开
         * [startNode, endNode)
         */
        private ListNode reverseKList(ListNode startNode, ListNode endNode) {
            ListNode pre = null;
            ListNode cur = startNode;
            ListNode next = startNode;

            while (cur != endNode) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    static class Practice {
        /**
         * 反转整个链表
         */
        public ListNode ReverseWholeLinkedList(ListNode head) {

            ListNode pre = null;
            ListNode cur = head, next = head;

            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }




    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}



