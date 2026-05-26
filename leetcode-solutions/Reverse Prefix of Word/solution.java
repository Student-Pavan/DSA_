1class Solution {
2    public String reversePrefix(String word, char ch) {
3        int idx = word.indexOf(ch);
4        if(idx == -1)
5            return word;
6
7        String prefix  = word.substring(0,idx+1);
8        StringBuilder sb = new StringBuilder(prefix);
9
10        return sb.reverse().toString() + word.substring(idx+1); 
11
12    }
13}