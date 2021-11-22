package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表 -> 字节面试题
 * <p>
 * 1 2 3 4 5
 * 1 5 2 4 3
 * <p>
 * l0 l1 l2 ... ln-1 ln
 * l0 ln l1 ln-2 l2 ....
 * <p>
 * <p>
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 */
public class ReorderList_143 {
    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        new Solution2().reorderList(head);
        assert head.val == 1;
        int a = 0;
    }


    /**
     * 第二种方法:
     * 目标链表: 将原链表的左半端和反转后的右半端合并后的结果。
     *
     * step1: 找到中间节点 -> lc876, 快慢指针
     * step2: 右半段翻转 -> lc206,  reverse
     * step3: 两个链表合并
     */
    static class Solution2 {
        public void reorderList(ListNode head) {



        }
    }


    /**
     * 简单的想法:
     * 因为这里用的是链表, 链表不好随机访问, 因此全便利, 用一个线性表ArrayList来存储, 然后重建
     */
    static class Solution1 {
        public void reorderList(ListNode head) {
            List<ListNode> list = new ArrayList<>();
            ListNode curNode = head;
            while (curNode != null) {
                list.add(curNode);
                curNode = curNode.next;
            }

            int v1 = 0;
            int v2 = list.size() - 1;
            while (v1 < v2) {
                list.get(v1).next = list.get(v2);
                v1++;
                if (v1 == v2) {
                    // 1, 2
                    break;
                }
                // 1, 2, 3
                list.get(v2).next = list.get(v1);
                v2--;
            }
            // 如果没有这句: Error - Found cycle in the ListNode
            list.get(v1).next = null;
        }
    }

    static class ListNode {
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
}
