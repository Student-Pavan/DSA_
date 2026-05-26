1class Solution {
2    public int lengthOfLastWord(String s) {
3        String str = s.trim();
4        int count = 0 ; 
5        for(int i = str.length()-1 ; i >= 0 ; i--){
6            if(Character.isWhitespace(str.charAt(i)))
7                break;
8            count++;
9        }
10        return count;
11    }
12}