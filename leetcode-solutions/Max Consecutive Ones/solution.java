1class Solution {
2    public int findMaxConsecutiveOnes(int[] nums) {
3
4        int count = 0;
5        int maxones = 0;
6
7        for(int i = 0; i < nums.length; i++){
8
9            if(nums[i] == 1){
10                count++;
11                maxones = Math.max(maxones, count);
12            }
13            else{
14                count = 0;
15            }
16        }
17
18        return maxones;
19    }
20}