1class Solution {
2    public int countPartitions(int[] nums) {
3        int[] left = new int[nums.length];
4        left[0] = nums[0];
5        for(int i = 1; i < nums.length - 1; i++){
6            left[i] = left[i - 1] + nums[i];
7        }
8        int[] right = new int[nums.length];
9        right[nums.length - 1] = nums[nums.length - 1];  
10
11        for(int i = nums.length - 2; i >= 1; i--){
12            right[i] = right[i + 1] + nums[i];
13        }
14
15
16        int count = 0;
17        for(int i = 0 ; i < right.length-1;i++){
18            if(((Math.abs(left[i]-right[i+1]))%2) == 0)
19                count++;
20        }
21        return count;
22        
23        
24
25    }
26}