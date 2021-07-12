package LeetCode;
// 2两数相加
// 倒序链表

public class addTwoNumbers2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        Solution solution = new Solution();
        solution.addTwoNumbers(l1, l2);
    }


    /**
     * Definition for singly-linked list.
     *
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null;
            ListNode work = null;

            int n1 = 0;
            int n2 = 0;
            int carry = 0;

            while (l1 != null || l2 != null) {
                if (l1 != null) {
                    n1 = l1.val;
                }
                else {
                    n1 = 0;
                }
                if (l2 != null) {
                    n2 = l2.val;
                }
                else {
                    n2 = 0;
                }

                int sum = n1 + n2 + carry;
                carry = sum / 10;
                if (head == null) {
                    head = new ListNode(sum % 10);
                    work = head;
                }
                else {
                    ListNode newNode = new ListNode(sum % 10);
                    work.next = newNode;
                    work = work.next;
                }

                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }

            if (carry != 0) {
                work.next = new ListNode(carry);
            }

            return head;
        }
    }

    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
