1class Solution {
2    public int smallestEvenMultiple(int n) {
3        if(n%2 == 0 && n%n == 0)
4            return n;
5        return n * 2;
6    }
7}