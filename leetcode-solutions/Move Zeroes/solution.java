1class Solution {
2    public void moveZeroes(int[] nums) {
3        int left = 0;
4        int right = 0;
5
6        while (right < nums.length) {
7            if (nums[right] == 0) {
8                right++;
9            } 
10            else {
11                if (nums[left] == 0) {
12                    int temp = nums[right];
13                    nums[right] = nums[left];
14                    nums[left] = temp;
15                }
16                left++;
17                right++;
18            }
19        }
20    }
21}