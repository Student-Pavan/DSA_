1class Solution {
2    public void sortColors(int[] nums) {
3        int left = 0;
4        int mid = 0;
5        int right = nums.length - 1;
6        while(mid <= right){
7            if(nums[mid] == 0){
8                int temp = nums[mid];
9                nums[mid] = nums[left];
10                nums[left] = temp;
11                left++;
12                mid++;
13            }
14            else if(nums[mid] == 2){
15                int temp = nums[mid];
16                nums[mid] = nums[right];
17                nums[right] = temp;
18                right--;
19            }
20            else if(nums[mid] == 1){
21                mid++;
22            }
23            else{
24                right--;
25            }
26        
27
28        }
29    }
30}