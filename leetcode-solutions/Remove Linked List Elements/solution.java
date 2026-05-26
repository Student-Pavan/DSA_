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
12    public ListNode removeElements(ListNode head, int val) {
13        while(head!= null && head.val == val){
14            head = head.next;
15        }
16
17        if(head == null)
18            return null;
19
20        
21        ListNode prev = head;
22        ListNode temp = head.next;
23        while(temp != null){
24            if(temp.val == val)
25                prev.next = temp.next;
26            else
27                prev = temp;
28
29            temp = temp.next;
30        }
31        return head;
32    }
33}