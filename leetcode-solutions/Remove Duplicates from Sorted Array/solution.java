1class Solution {
2    public int removeDuplicates(int[] nums) {
3        int count=1;
4        int copy=0;
5        nums[copy++]=nums[0];
6        int val = nums[0];
7        for(int i = 1 ;i < nums.length; i++ ){
8             
9             if( val != nums[i] ){
10                val = nums[i];
11                nums[copy++] = nums[i];
12                count++;
13            }
14        }
15        return count;
16       
17    }
18}