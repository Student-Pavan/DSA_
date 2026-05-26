1class Solution {
2    public int countOdds(int low, int high) {
3        int total = high - low + 1;
4
5        if (low % 2 == 0 && high % 2 == 0) {// for even ends 
6            return total / 2;//half elements are odd
7        } 
8        else if (low % 2 == 1 && high % 2 == 1) {// for odd ends
9            return total / 2 + 1; //half+1 elements are odd
10        } 
11        else {
12            return total / 2; 
13        }
14    }
15}
16