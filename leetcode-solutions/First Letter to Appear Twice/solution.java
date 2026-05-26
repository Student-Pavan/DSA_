1class Solution {
2    public char repeatedCharacter(String s) {
3        int[] charmap = new int[26];
4        for(int i = 0 ; i < s.length() ; i++){
5            char currchar = s.charAt(i);
6            if(charmap[currchar - 'a'] == 1){
7                return currchar;
8            }
9            charmap[currchar - 'a'] = 1;
10        }
11        return '\0';
12    }
13}