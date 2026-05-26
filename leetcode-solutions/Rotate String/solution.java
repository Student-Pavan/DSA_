1class Solution {
2    public boolean rotateString(String s, String goal) {
3        if(s.length() !=  goal.length()){
4            return false;
5        }
6        if((s+s).contains(goal))
7            return true;
8        return false;
9    }
10}