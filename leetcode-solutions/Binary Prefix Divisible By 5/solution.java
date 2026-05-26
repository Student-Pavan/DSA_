1class Solution {
2    public List<Boolean> prefixesDivBy5(int[] nums) {
3        List<Boolean> ans = new ArrayList<>();
4        int value = 0;
5
6        for (int bit : nums) {
7            value = (value * 2 + bit) % 5; 
8            ans.add(value == 0);
9        }
10
11        return ans;
12    }
13}
14