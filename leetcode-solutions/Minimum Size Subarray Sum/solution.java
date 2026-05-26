1class Solution {
2    public int minSubArrayLen(int target, int[] nums) {
3
4        int len = Integer.MAX_VALUE;
5        int left = 0;
6        int sum = 0;
7        for(int right = 0 ; right < nums.length ; right++){
8            sum += nums[right];
9
10            while(sum >= target){
11                len = Math.min(len,right - left + 1);
12                sum -= nums[left];
13                left++;
14            }
15
16        }
17        return len == Integer.MAX_VALUE ? 0 : len;
18
19    }
20}