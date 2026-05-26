1class Solution {
2    public int numSubarrayProductLessThanK(int[] nums, int k) {
3        if(k <= 1){
4            return 0;
5        }
6        int left = 0;
7        int count = 0;
8        int prod = 1;
9
10        for(int right = 0 ; right < nums.length ; right++){
11            prod *= nums[right];
12
13            while(prod >= k){
14                prod /= nums[left];
15                left++;
16            }
17            count += right - left + 1;
18        }
19        return count;
20    }
21}