1class Solution {
2    public int waysToSplitArray(int[] nums) {
3        long ls[] = new long[nums.length];
4        long rs[] = new long[nums.length];
5        // int res[] = new int[nums.length];
6
7        ls[0] = nums[0];
8        for(int i = 1 ; i < nums.length ; i++ )
9            ls[i] = ls[i-1] + nums[i];
10
11        for(int i = nums.length-2 ; i >= 0  ; i-- )
12            rs[i] = rs[i+1] + nums[i+1];
13        
14        int count =0;
15        for(int i = 0 ; i < nums.length-1 ; i++){
16            if(ls[i] >= rs[i]){
17                count++;
18            }
19        }
20        return count;
21            
22    
23    }
24}