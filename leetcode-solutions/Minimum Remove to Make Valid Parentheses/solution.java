1class Solution {
2    public String minRemoveToMakeValid(String s) {
3        Stack<Integer> stack = new Stack<>();
4
5        for(int i = 0 ; i < s.length() ; i++){
6            char ch = s.charAt(i);
7             if (ch == '(') {
8                stack.push(i);
9            }
10
11            else if (ch == ')') {
12
13                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
14                    stack.pop();
15                } else {
16                    stack.push(i);
17                }
18            }
19        }
20
21        StringBuilder sb = new StringBuilder(s);
22
23        while(!stack.isEmpty()){
24            sb.deleteCharAt(stack.pop());
25        }
26        
27        
28        return sb.toString();
29    }
30}