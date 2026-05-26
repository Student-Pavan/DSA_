1class Solution {
2    public String removeDuplicates(String s) {
3
4        Stack<Character> stack = new Stack<>();
5
6        for(int i = 0; i < s.length(); i++) {
7
8            char ch = s.charAt(i);
9
10            if(!stack.isEmpty() && stack.peek() == ch) {
11                stack.pop();
12            } 
13            else {
14                stack.push(ch);
15            }
16        }
17
18        StringBuilder str = new StringBuilder();
19
20        for(char c : stack) {
21            str.append(c);
22        }
23
24        return str.toString();
25    }
26}