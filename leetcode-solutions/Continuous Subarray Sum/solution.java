1class Solution {
2    public boolean checkSubarraySum(int[] nums, int k) {
3        HashMap<Integer, Integer> map = new HashMap<>();
4        int prefixsum = 0;
5        int maxlen = 0;
6
7        map.put(0, -1);
8        for (int i = 0; i < nums.length; i++) {
9            prefixsum += nums[i];
10
11            int rem = prefixsum % k;
12
13            if (map.containsKey(rem)) {
14                if (i - map.get(rem) >= 2) {
15                    return true;
16                }
17            } else {
18                map.put(rem, i);
19            }
20
21        }
22        return false;
23    }
24}