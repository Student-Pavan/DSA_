1class Solution {
2    public int climbStairs(int n) {
3        if( n == 0 || n == 1)
4            return 1;
5        if( n == 2 )
6            return 2;
7        
8        int first = 1;
9        int second = 2;
10        int third = 0;
11        int i = 2 ;
12        while(i < n){
13            third = first + second;
14            first = second;
15            second = third;
16            i++;
17        }
18        return third;
19    }
20}