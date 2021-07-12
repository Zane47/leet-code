package LeetCode;
public class MergeTwoList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 1;
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode();
        l2.val = 1;
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        new Solution().mergeTwoLists(l1, l2);
    }
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode result = new ListNode();
            ListNode node = result;
            ListNode new1;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    new1 = new ListNode(l1.val, null);
                    node.next = new1;
                    l1 = l1.next;
                }
                else {
                    new1 = new ListNode(l2.val, null);
                    node.next = new1;
                    l2 = l2.next;
                }
                node = node.next;
            }

            if (l1 == null) {
                // list1遍历完成，接下来只需要把list2的东西都放入即可
                node.next = l2;
            }

            if (l2 == null) {
                // list1遍历完成，接下来只需要把list2的东西都放入即可
                node.next = l1;
            }

            return result.next;


        }
    }

    public static class ListNode {
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

