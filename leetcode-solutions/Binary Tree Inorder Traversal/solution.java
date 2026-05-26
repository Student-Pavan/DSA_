1/**
2 * Definition for a binary tree node.
3 * public class TreeNode {
4 *     int val;
5 *     TreeNode left;
6 *     TreeNode right;
7 *     TreeNode() {}
8 *     TreeNode(int val) { this.val = val; }
9 *     TreeNode(int val, TreeNode left, TreeNode right) {
10 *         this.val = val;
11 *         this.left = left;
12 *         this.right = right;
13 *     }
14 * }
15 */
16class Solution {
17    public List<Integer> inorderTraversal(TreeNode root) {
18        List<Integer> ls = new ArrayList<>();
19        inorder(root,ls);
20        return ls;
21    
22    }
23    public void inorder(TreeNode root ,List<Integer> ls ) {
24        if(root == null){
25            return;
26        }
27        inorder(root.left,ls);
28        ls.add(root.val);
29        inorder(root.right,ls);
30
31
32    }
33}