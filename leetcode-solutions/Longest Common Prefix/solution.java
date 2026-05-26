1class Solution {
2    public String longestCommonPrefix(String[] strs) {
3        String str = strs[0];
4        String common = "";
5        if(strs.length == 1 && strs[0].length() == 1)
6            return strs[0];
7        for(int i = 1 ; i < strs.length; i++ ){
8            String currstr = strs[i];
9            int len = Math.min(str.length(), currstr.length());
10            common = "";
11            for(int j = 0; j < len ; j++){
12                if(str.charAt(j) == currstr.charAt(j)){
13                    common += str.charAt(j); 
14                }
15                else
16                    break;
17            }
18            str = "";
19            str = common;
20        }
21        return common;
22
23    }
24}