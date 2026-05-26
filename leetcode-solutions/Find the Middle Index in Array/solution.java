1class Solution {
2    public int findMiddleIndex(int[] nums) {
3        int ls[] = new int[nums.length];
4        int rs[] = new int[nums.length];
5        int res[] = new int[nums.length];
6
7        for(int i = 1 ; i < nums.length ; i++ )
8            ls[i] = ls[i-1] + nums[i-1];
9
10        for(int i = nums.length-2 ; i >= 0  ; i-- )
11            rs[i] = rs[i+1] + nums[i+1];
12
13        for(int i = 0 ; i < nums.length ; i++){
14            if(ls[i] == rs[i]){
15                return i;
16            }
17        }
18        return -1;
19            
20    }
21}