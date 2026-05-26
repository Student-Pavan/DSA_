1class Solution {
2    public int minimumOperations(int[] nums) {
3        int count = 0;
4        for(int num : nums){
5            if(num%3 != 0){
6                count++;
7            }
8        }
9        return count;
10    }
11}