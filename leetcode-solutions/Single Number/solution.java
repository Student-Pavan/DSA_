1class Solution {
2    public int singleNumber(int[] nums) {
3        int singleElement = 0;
4        if(nums.length == 1){
5            return nums[0];
6        }
7        else{
8            int range = 0;
9            int min = nums[0], max = nums[0];
10
11            for (int n : nums) {
12                min = Math.min(min, n);
13                max = Math.max(max, n);
14            }
15            int shift = -min; 
16            int[] freq = new int[max - min + 1];
17            for(int num : nums){
18                freq[num + shift]++;
19            }
20           
21            for(int i = 0 ; i < freq.length ; i++){
22                if(freq[i] == 1)
23                    singleElement = i-shift;
24
25            }
26        }
27
28        return singleElement;
29
30    }
31}