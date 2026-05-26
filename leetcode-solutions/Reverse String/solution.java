1class Solution {
2    public void reverseString(char[] s) {
3        for(int i = 0; i < s.length/2 ;i++){
4            int front = i;
5            int back = s.length - i - 1;
6
7            char frontchar = s[front];
8            char backchar = s[back];
9
10            s[back] = frontchar;
11            s[front] =  backchar;
12
13
14        }
15        
16
17    }
18}