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
12    public ListNode removeNthFromEnd(ListNode head, int n) {
13        if(head == null && head.next == null)
14            return null;
15        
16        int size = 0 ; 
17        ListNode curr = head;
18
19        while(curr != null){
20            curr = curr.next;
21            size++;
22        }
23
24        if(n == size)
25            return head.next;
26
27        int indextosearch = size - n;
28        ListNode prev = head;
29        int i = 1 ;
30        while(i < indextosearch){
31            prev = prev.next;
32            i++;
33        }
34        prev.next = prev.next.next;
35        return head;
36    }
37}