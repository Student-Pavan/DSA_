1class Solution {
2    public int[] searchRange(int[] nums, int target) {
3        if(nums.length == 0)
4            return new int[] {-1,-1};
5
6        int first = firstOccurence(0, nums.length-1, target ,nums);
7        int last = lastOccurence(0, nums.length-1, target ,nums);
8
9        return new int[] {first,last};
10        
11    }
12    public int firstOccurence(int left, int right , int target , int[] arr){
13        int ans = -1;
14        while(left <= right){
15            int mid = left + (right - left) /2;
16
17            if(arr[mid] == target){
18                ans = mid;
19                right = mid -1;
20            }
21            else if (arr[mid] > target){
22                right = mid-1;
23            }
24            else {
25                left = mid + 1 ;
26            }
27
28        }
29        return ans;
30    }
31      public int lastOccurence(int left, int right , int target , int[] arr){
32        int ans = -1;
33        while(left <= right){
34            int mid = left + (right - left) /2;
35
36            if(arr[mid] == target){
37                ans = mid;
38                left = mid + 1;
39            }
40            else if (arr[mid] > target){
41                right = mid-1;
42            }
43            else {
44                left = mid + 1 ;
45            }
46
47        }
48        return ans;
49    }
50}