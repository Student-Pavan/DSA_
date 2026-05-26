1class Solution {
2    public int trap(int[] height) {
3        int leftmax = height[0];
4        int rightmax = height[height.length - 1];
5        int left = 0;
6        int right = height.length - 1;
7        int water = 0;
8        while(left < right){
9            if(leftmax < rightmax){
10                left++;
11                leftmax = Math.max(leftmax,height[left]);
12                water += leftmax - height[left];
13            }
14            else{
15                right--;
16                rightmax = Math.max(rightmax,height[right]);
17                water += rightmax - height[right];
18            }
19        }
20        return water;
21    }
22}