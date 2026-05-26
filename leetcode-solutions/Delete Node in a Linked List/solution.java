1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode(int x) { val = x; }
7 * }
8 */
9class Solution {
10    public void deleteNode(ListNode node) {
11        if(node == null || node.next == null){
12            return;
13        }
14        node.val = node.next.val;
15        node.next = node.next.next;
16    }
17}