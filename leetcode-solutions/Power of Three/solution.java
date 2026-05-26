1class Solution {
2    public boolean isPowerOfThree(int n) {
3        if(n <= 0)
4            return false;
5        while((n % 3) == 0){
6            n /= 3;
7        }
8        return n == 1;
9    }
10}