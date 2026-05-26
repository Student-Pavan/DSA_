1class Solution {
2    public boolean isPalindrome(String s) {
3        String cleanedstring = s.replaceAll("[^a-zA-Z0-9]", "").trim().toLowerCase();
4
5        for(int i = 0 ; i < cleanedstring.length()/2 ;i++){
6            char frontchar = cleanedstring.charAt(i);
7            char backchar = cleanedstring.charAt(cleanedstring.length()-1-i);
8
9            if(frontchar!=backchar){
10                return false;
11            }
12        }
13        return true;
14    }
15}