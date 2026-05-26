1class Solution {
2    List<String> ls = new ArrayList<>();
3    boolean found = false;
4
5    public String getPermutation(int n, int k) {
6        StringBuilder sb = new StringBuilder();
7        for(int i = 1 ; i <= n ; i++)
8            sb.append(i);
9        
10        getPermutations(sb.toString() , "" , k);
11
12        return ls.get(k-1);
13
14       
15    }
16    public void getPermutations(String str , String permutation ,int k ){
17        if(found) 
18            return;
19        if(str.length() == 0){
20            ls.add(permutation); 
21            if(ls.size() == k)
22                found = true;
23            return;
24        }
25        for(int i = 0 ; i < str.length() ; i++){
26            char currchar = str.charAt(i);
27            String newstr = str.substring(0,i) + str.substring(i+1);
28            getPermutations(newstr,permutation+currchar , k);
29        }
30    }
31}