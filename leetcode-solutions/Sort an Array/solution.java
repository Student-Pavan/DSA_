1class Solution {
2    public int[] sortArray(int[] nums) {
3        divide(nums, 0, nums.length-1);
4
5        return nums;
6    }
7    public void divide(int[] arr, int si, int ei){
8        if(si >= ei)
9            return;
10
11
12        int mid = si + (ei - si) /2 ;
13
14        divide(arr, si, mid);
15        divide(arr, mid+1, ei);
16        conquer(arr, si, mid, ei);
17        
18    }
19    public void conquer(int[] arr, int si, int mid, int ei){
20        int merge[] = new int[ei - si + 1];
21
22        int index1 = si;
23        int index2 = mid+1;
24        int x = 0; 
25        while(index1 <= mid && index2 <= ei){
26            if(arr[index1] <= arr[index2]){
27                merge[x++] = arr[index1++];
28            }else
29                merge[x++] = arr[index2++];
30        }
31        while(index1 <= mid)
32            merge[x++] = arr[index1++];
33
34        while(index2 <= ei)
35            merge[x++] = arr[index2++];
36
37        for(int i = 0 , j = si ;  i < merge.length; i++ , j++)
38            arr[j] = merge[i];
39
40
41    }
42}