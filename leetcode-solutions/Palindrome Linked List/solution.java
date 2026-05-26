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
12    public boolean isPalindrome(ListNode head) {
13        if(head == null || head.next == null)
14            return true;
15        ListNode midnode = getmidnode(head);
16        ListNode shf = reverse(midnode.next);
17        ListNode fhf = head;
18        while(shf != null){
19            if(fhf.val != shf.val)
20                return false;
21            fhf = fhf.next;
22            shf = shf.next;
23        }
24        return true;
25
26    }
27    ListNode reverse(ListNode head){
28        ListNode prev = null;
29        ListNode curr = head;
30        while(curr != null){
31            ListNode next = curr.next;
32            curr.next = prev;
33            prev = curr;
34            curr = next;
35        }
36        return prev;
37    }
38
39    ListNode getmidnode(ListNode head){
40        ListNode hare = head;
41        ListNode turtle = head;
42
43        while(hare.next != null &&  hare.next.next != null){
44            turtle = turtle.next;
45            hare = hare.next.next;
46        }
47        return turtle;
48    }
49    
50}