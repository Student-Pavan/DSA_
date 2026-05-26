1class Solution {
2
3    public List<List<Integer>> threeSum(int[] nums) {
4
5        Arrays.sort(nums);
6
7        List<List<Integer>> ls = new ArrayList<>();
8
9        int n = nums.length - 1;
10
11        for (int i = 0; i < n; i++) {
12
13            if (i > 0 && nums[i] == nums[i - 1]) {
14                continue;
15            }
16
17            int left = i + 1;
18            int right = n;
19
20            while (left < right) {
21
22                int sum = nums[i] + nums[left] + nums[right];
23
24                if (sum == 0) {
25
26                    ls.add(Arrays.asList(nums[i], nums[left], nums[right]));
27
28                    left++;
29                    right--;
30
31                    while (left < right && nums[left] == nums[left - 1]) {
32                        left++;
33                    }
34
35                    while (left < right && nums[right] == nums[right + 1]) {
36                        right--;
37                    }
38                }
39                else if (sum > 0) {
40                    right--;
41                }
42                else {
43                    left++;
44                }
45            }
46        }
47
48        return ls;
49    }
50}