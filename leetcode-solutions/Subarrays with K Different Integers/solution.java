1class Solution {
2
3    public int subarraysWithKDistinct(int[] nums, int k) {
4        return atMost(nums, k) - atMost(nums, k - 1);
5    }
6
7    private int atMost(int[] nums, int k) {
8
9        HashMap<Integer, Integer> map = new HashMap<>();
10
11        int left = 0;
12        int count = 0;
13
14        for (int right = 0; right < nums.length; right++) {
15
16            map.put(nums[right],
17                    map.getOrDefault(nums[right], 0) + 1);
18
19            while (map.size() > k) {
20
21                map.put(nums[left],
22                        map.get(nums[left]) - 1);
23
24                if (map.get(nums[left]) == 0) {
25                    map.remove(nums[left]);
26                }
27
28                left++;
29            }
30
31            count += right - left + 1;
32        }
33
34        return count;
35    }
36}