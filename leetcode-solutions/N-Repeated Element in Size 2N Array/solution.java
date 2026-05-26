1class Solution {
2    public int repeatedNTimes(int[] nums) {
3        HashSet<Integer> hs = new HashSet<>();
4
5        for(int i = 0 ; i < nums.length ; i++){
6            if(hs.contains(nums[i])){
7                return nums[i];
8            }
9            hs.add(nums[i]);
10        }
11        return -1;
12    }
13}