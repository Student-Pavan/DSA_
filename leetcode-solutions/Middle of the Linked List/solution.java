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
12    public ListNode middleNode(ListNode head) {
13
14        ListNode hare = head;
15        ListNode turtle = head;
16        while (hare.next != null && hare.next.next != null){
17            turtle = turtle.next;
18            hare = hare.next.next;
19        }
20        if(hare.next != null){
21            return turtle.next;
22        }
23        return turtle;
24    }
25   
26}