package leetcode.linkedlist;

/**
 * 请编写一个函数，用于删除单链表中某个特定节点 。
 * 在设计函数时需要注意，你无法访问链表的头节点head ，只能直接访问要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 * 注意点:
 * 法访问链表的头节点head ，只能直接访问要被删除的节点 。
 *
 */
public class DeleteNodeInLinkedList_273 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
    }


    /**
     * 无法访问head, 那么就无法知道要删除结点node的前一个结点
     * 传统的链表结点删除方法无法使用
     *
     * 所以将node的下一个结点next和该结点node的val交换, 然后删除next结点即可
     */
    class Solution {
        public void deleteNode(ListNode node) {
            ListNode next = node.next;
            node.val = next.val;
            node.next = next.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

