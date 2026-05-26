1class Solution {
2    public boolean isPalindrome(int x) {
3        String str = String.valueOf(x);
4        int rem = 0;
5        int rev = 0;
6        int q = x ;
7
8        if(x >=  0){
9           for(int i = 0; i < str.length() ; i++){
10                rem = q % 10;
11                rev = rev*10 + rem;
12                q /= 10;
13            } 
14            if(rev == x)
15                return true;
16        }
17    
18
19        return false;
20    }
21}