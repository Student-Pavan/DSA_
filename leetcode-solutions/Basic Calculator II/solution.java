1class Solution {
2    public int calculate(String s) {
3        Stack<Integer> st = new Stack<>();
4        int num = 0;
5        char op = '+';
6
7        for(int i = 0 ; i <= s.length() ; i++){
8            char ch = (i == s.length()) ? '+' : s.charAt(i);
9
10            if(Character.isDigit(ch)){
11                num = num*10 + (ch - '0');
12
13            }
14            else if(ch != ' '){
15                if(op == '+'){
16                    st.push(num);
17                }
18                else if(op == '-'){
19                    st.push(-num);
20                }
21                else if(op == '*'){
22                    st.push(st.pop()*num);
23                }
24                else if(op == '/'){
25                    st.push(st.pop()/num);
26                }
27                op = ch;
28                num = 0;
29            }
30               
31           
32             
33        }
34
35        int ans = 0;
36        while(!st.isEmpty()){
37            ans += st.pop();
38        }
39
40        return ans;
41    }
42}