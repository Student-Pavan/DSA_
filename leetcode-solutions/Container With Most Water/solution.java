1class Solution {
2    public int maxArea(int[] height) {
3        int left = 0;
4        int right = height.length - 1;
5        int mostWater = 0;
6        while(left < right){
7            int watertraped = Math.min(height[left], height[right]) * (right - left);
8            if(watertraped > mostWater){
9                mostWater = watertraped;
10            }
11            else if(height[left] < height[right]){
12                left++;
13            }
14            else{
15                right--;
16            }
17        }
18        return mostWater;
19    }
20}