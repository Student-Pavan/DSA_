1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode() {}
7 *     ListNode(int val) { this.val = val; }
8 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
9 * }
10 */
11class Solution {
12    public ListNode reverseList(ListNode head) {
13        ListNode previous = null;
14        ListNode current = head;
15
16        while(current != null){
17            ListNode next = current.next;
18            current.next = previous;
19
20            previous = current;
21            current = next;
22
23        }
24        return previous;
25    }
26
27
28}