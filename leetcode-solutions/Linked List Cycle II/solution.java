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
13    public ListNode detectCycle(ListNode head) {
14        if(head == null || head.next == null )
15            return null;
16
17        ListNode hare = head;
18        ListNode turtle = head;
19        ListNode mid = null;
20        ListNode strt = head
21        ;
22        while(hare.next != null && hare.next.next != null){
23            hare = hare.next.next;
24            turtle = turtle.next;
25            if(hare == turtle){
26                mid = turtle;
27                break;
28            }
29        }
30        if(mid == null)
31            return null;
32        while(mid != strt){
33            mid = mid.next;
34            strt = strt.next;
35        }
36
37    return mid;
38    }
39}