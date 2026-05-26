1class Solution {
2    public int search(int[] nums, int target) {
3        int left = 0;
4        int right = nums.length-1;
5        while(left <= right){
6            int mid =  left + (right - left)/2;
7            if(nums[mid] == target)
8                return mid;
9            else if(nums[mid] > target)
10                right = mid - 1;
11            else 
12                left = mid + 1;
13        }
14        return -1;
15    }
16} 