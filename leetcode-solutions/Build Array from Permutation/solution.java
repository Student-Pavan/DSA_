1class Solution {
2    public int[] buildArray(int[] nums) {
3        int ans[] = new int[nums.length];
4        for(int i = 0 ; i < nums.length; i++){
5            int num1 = nums[i];
6            int num2 = nums[num1];
7            ans[i] = num2;
8
9        }
10        return ans;
11    }
12}