1class Solution {
2    public int[] leftRightDifference(int[] nums) {
3
4       int ls[] = new int[nums.length];
5       int rs[] = new int[nums.length];
6       int res[] = new int[nums.length];
7
8        for(int i = 1 ; i < nums.length ; i++){
9                ls[i] = ls[i-1] + nums[i-1];
10        }
11
12        for(int i = nums.length -2 ; i >= 0 ; i--){
13                rs[i] = rs[i+1] + nums[i+1];
14        }
15        for(int i = 0 ; i < nums.length ; i ++){
16            res[i] = Math.abs(ls[i] - rs[i]);
17
18        }
19        return res;
20
21
22
23
24    }
25}