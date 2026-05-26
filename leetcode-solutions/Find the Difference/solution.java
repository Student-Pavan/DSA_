1class Solution {
2    public char findTheDifference(String s, String t) {
3        int alphabets[] = new int[26];
4        for(char c : s.toCharArray())
5            alphabets[c-'a']++;
6        for(char c : t.toCharArray()){
7            int idx = c-'a';
8            if(alphabets[idx] == 0) 
9                return c;
10            alphabets[idx]--;
11        }
12        return ' '; 
13    }
14}