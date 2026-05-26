1class Solution {
2    public int[] twoSum(int[] nums, int target) {
3        List<Integer> list = new ArrayList<Integer>();
4        for(int i=0; i<nums.length; i++){
5            for(int j=i+1;j<nums.length ; j++){
6                if(nums[i]+nums[j] == target){
7                   return new int[]{i,j};
8                }
9
10            }
11
12
13        }
14        return new int[]{-1,-1};
15    }
16}