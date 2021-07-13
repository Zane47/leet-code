package leetcode;


import java.util.HashSet;


//142
public class DetectCycle_142 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // head = [3,2,0,-4], pos = 1
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        solution.detectCycle(head);
    }


    // 双指针
    static class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode fastNode = head;
            ListNode slowNode = head;

            while (fastNode != null) {
                if (slowNode.next != null) {
                    slowNode = slowNode.next;
                }
                else {
                    return null;
                }
                if (fastNode.next != null && fastNode.next.next != null) {
                    fastNode = fastNode.next.next;
                }
                else {
                    return null;
                }

                // 相遇了，有环
                if (fastNode == slowNode) {
                    ListNode v1 = head;
                    while (v1 != slowNode) {
                        slowNode = slowNode.next;
                        v1 = v1.next;
                    }
                    return v1;
                }

            }
            return null;
        }
    }


    // 新增hashSet，需要空间。标记访问过得节点
    static class Solution1 {
        public ListNode detectCycle(ListNode head) {
            // HashMap<ListNode, Integer> map1 = new HashMap<ListNode, Integer>();
            HashSet<ListNode> set1 = new HashSet<>();
            ListNode currentNode = head;
            while (currentNode != null) {
                if (set1.contains(currentNode)) {
                    return currentNode;
                }
                else {
                    set1.add(currentNode);
                }

                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                else {
                    return null;
                }
            }
            return null;
        }
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
