1class Solution {
2    public String smallestSubsequence(String s) {
3        int[] lastindex = new int[26];
4        boolean[] visited = new boolean[26];
5
6        for (int i = 0; i < s.length(); i++) {
7            lastindex[s.charAt(i) - 'a'] = i;
8        }
9
10        StringBuilder sb = new StringBuilder();
11
12        for (int i = 0; i < s.length(); i++) {
13
14            char ch = s.charAt(i);
15
16            if (visited[ch - 'a']) {
17                continue;
18            }
19
20            
21            while (
22                sb.length() > 0 &&
23                sb.charAt(sb.length() - 1) > ch &&
24                lastindex[sb.charAt(sb.length() - 1) - 'a'] > i
25            ) {
26
27                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
28                sb.deleteCharAt(sb.length() - 1);
29            }
30
31            sb.append(ch);
32            visited[ch - 'a'] = true;
33        }
34
35        return sb.toString();
36    }
37}