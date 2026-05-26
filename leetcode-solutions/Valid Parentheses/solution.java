1class Solution {
2    public boolean isValid(String s) {
3        Stack<Character> st = new Stack<>();
4
5        for(char c : s.toCharArray()){
6            if(c == '[')
7                st.push(']');
8            else if(c == '{')
9                st.push('}');
10            else if(c == '(')
11                st.push(')');
12
13            else 
14                if(st.isEmpty() || st.pop() != c)
15                    return false;
16
17        } 
18        return st.isEmpty();
19    }
20}