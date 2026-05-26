1class Solution {
2    public int[] sortedSquares(int[] nums) {
3        int[] result = new int[nums.length]; 
4        int forward = 0;
5        int backward = nums.length - 1;
6        int position = nums.length - 1;
7
8        while(forward <= backward){
9            int ele1 = nums[forward] * nums[forward];
10            int ele2 = nums[backward] * nums[backward];
11            
12            if(ele2 > ele1){
13                result[position--] = ele2;
14                backward--;
15            }
16            else{
17                result[position--] = ele1;
18                forward++;
19            }
20
21        }
22       
23        return result;
24    }
25}