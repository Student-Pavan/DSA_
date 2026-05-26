1class Solution {
2    public int hammingWeight(int n) {
3        String binary = Integer.toBinaryString(n);
4        int count = 0;
5        for(int i = 0 ; i < binary.length() ; i++){
6            if(binary.charAt(i) == '1')
7                count++;
8        }
9        return count;
10    }
11}