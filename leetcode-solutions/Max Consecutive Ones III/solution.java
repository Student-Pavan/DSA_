1class Solution {
2    public int longestOnes(int[] nums, int k) {
3        int zerocount = 0;
4        int maxones = 0;
5
6        int left = 0;
7        for(int right = 0 ; right < nums.length ; right++){
8            if(nums[right] == 0){
9                zerocount++;
10            }
11            if(zerocount > k){
12                if(nums[left] == 0){
13                    zerocount--;
14                }
15                left++;
16            }
17            maxones = Math.max(maxones,right - left +1);
18        }
19        return maxones;
20    }
21}