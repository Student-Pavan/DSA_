1/**
2 * Definition for singly-linked list.
3 * class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode(int x) {
7 *         val = x;
8 *         next = null;
9 *     }
10 * }
11 */
12public class Solution {
13    public boolean hasCycle(ListNode head) {
14        if(head == null){
15            return false;
16        }
17        ListNode hare = head;
18        ListNode turtle = head;
19        while(hare.next != null && hare.next.next != null){
20            turtle = turtle.next;
21            hare = hare.next.next;
22            if(hare == turtle)
23                return true;
24            
25        }
26       
27        return false;
28    }
29}