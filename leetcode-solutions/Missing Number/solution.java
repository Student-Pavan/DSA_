1class Solution {
2    public int missingNumber(int[] nums) {
3        Arrays.sort(nums);
4        int val = 0;
5        for(int i = 0 ; i < nums.length ; i++){
6            if(nums[i] != i){
7                return i;
8            }
9            val++;
10            
11        }
12        return val;
13    }
14}