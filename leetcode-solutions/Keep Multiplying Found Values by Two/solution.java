1class Solution {
2    public int findFinalValue(int[] nums, int original) {
3        List<Integer> list = new ArrayList<>();
4        for(int i=0 ; i < nums.length ; i++){
5            list.add(nums[i]);
6        }
7        for(int i=0 ; i < list.size() ; i++){
8            if(list.contains(original)){
9                original = original*2;
10            }
11            else
12                break;
13        }
14
15        // original = BS(original,nums);
16
17        return original;
18    }
19    // public int BS(int original , int nums[] ){
20    //     int low = 0;
21    //     int high = nums.length-1;
22        
23    //     while(low <= high){
24    //        int  mid = low+(high-low)/2;
25
26    //         if(nums[mid] == original){
27    //              original = nums[mid]*2;
28    //              BS(original , nums );
29    //         }
30            
31    //         else if(mid > original)
32    //             high = mid-1;
33    //         else 
34    //             low = mid-1;
35    //     }
36    //     return original;
37
38    // }
39}