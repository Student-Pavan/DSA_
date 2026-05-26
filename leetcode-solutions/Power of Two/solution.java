1class Solution {
2    public boolean isPowerOfTwo(int n) {
3        if(n <= 0)
4            return false;
5        while(n %2 == 0)
6            n /=2;
7
8        return n ==1;
9    }
10}