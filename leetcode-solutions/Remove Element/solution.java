1class Solution {
2    public int removeElement(int[] nums, int val) {
3        int k=0;
4        for(int i =0 ; i < nums.length; i++)
5            if (nums[i] != val) {
6                nums[k] = nums[i];
7                k++;
8            }
9        return k;
10    }
11}