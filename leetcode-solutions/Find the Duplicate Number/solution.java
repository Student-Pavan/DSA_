1class Solution {
2    public int findDuplicate(int[] nums) {
3        int range = 0;
4        for(int i = 0  ; i < nums.length ; i++)
5            range = Math.max(nums[i] , range);
6        
7        int freq[] = new int[range+1];
8
9        for(int val : nums)
10            freq[val]++;
11        int max = 0 ;
12        for(int i = 0 ; i < freq.length ; i++){
13            if(freq[i] > 1)
14                max = i;
15            
16        }
17        return max;
18    }
19}