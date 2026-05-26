1class Solution {
2    public int maxSubArray(int[] nums) {
3        int maxsum = nums[0];
4        int sum = 0;
5        for(int n : nums){
6            sum += n;
7            maxsum = Math.max(sum,maxsum);
8            
9            if(sum < 0){
10                sum = 0;
11            }
12            
13        }
14        return maxsum;
15    }
16}