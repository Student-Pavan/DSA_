1class Solution {
2    public int minOperations(int[] nums, int k) {
3        int sum = 0 ;
4        for(int i = 0 ; i < nums.length ;i++){
5            sum += nums[i]; 
6        }
7        if((sum % k) == 0 ){
8            return 0;
9        }
10        return (sum%k); 
11    }
12}