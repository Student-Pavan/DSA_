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
17    public List<List<Integer>> levelOrder(TreeNode root) {
18        
19        List<List<Integer>> ls = new ArrayList<>();
20        Queue<TreeNode> q = new LinkedList<>();
21
22        if(root == null){
23            return ls;
24        }
25        
26        q.add(root);
27        q.add(null);
28
29        List<Integer> inner = new ArrayList<>();
30
31        while(!q.isEmpty()){
32            TreeNode currnode = q.poll();
33            if(currnode == null){
34                ls.add(inner);
35                inner = new ArrayList<>();
36
37                if(q.isEmpty())
38                    break;
39                else
40                    q.add(null);
41            }
42            else{
43                inner.add(currnode.val);
44                if(currnode.left != null)
45                    q.add(currnode.left);
46                 if(currnode.right != null)
47                    q.add(currnode.right);
48            }
49        }
50        return ls;
51    
52    }
53}