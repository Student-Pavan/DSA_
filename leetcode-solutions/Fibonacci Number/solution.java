1class Solution {
2    public int fib(int n) {
3        if(n == 0)
4            return 0;
5        if(n == 1 || n == 2)
6            return 1;
7        
8        return fib(n-1)+fib(n-2);
9        
10
11    }
12}