1class Solution {
2    public int[] productExceptSelf(int[] nums) {
3        int[] lp = new int[nums.length];
4        int[] rp = new int[nums.length];
5        int[] res = new int[nums.length];
6
7        lp[0] = 1;
8        for(int i = 1 ; i < nums.length ; i++){
9            lp[i] = lp[i-1] * nums[i-1];
10        }
11        rp[nums.length-1] = 1;
12        for(int i = nums.length-2 ; i >= 0 ; i--){
13            rp[i] = rp[i+1] * nums[i+1];
14        }
15
16        for(int  i = 0 ; i < nums.length ; i++)
17            res[i] = lp[i] * rp[i];
18
19        return res;
20
21
22
23
24    }
25}