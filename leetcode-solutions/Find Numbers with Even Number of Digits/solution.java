1class Solution {
2    public int findNumbers(int[] nums) {
3        int count = 0;
4        for(int i = 0 ; i < nums.length ;i++ ){
5            String len = String.valueOf(nums[i]);
6            if(len.length() % 2 == 0)
7                 count++;
8        }
9        return count;
10    }
11}