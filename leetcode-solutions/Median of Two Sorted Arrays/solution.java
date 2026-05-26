1class Solution {
2    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
3        int m = nums1.length;
4        int n = nums2.length;
5        int[] merger = new int[m+n];
6        int i = 0,j=0,k=0;
7        while(i < nums1.length && j <nums2.length){
8            if(nums1[i] < nums2[j])
9                merger[k++] = nums1[i++];
10            else
11                merger[k++] = nums2[j++];
12             
13        }
14        while(i < nums1.length)
15             merger[k++] = nums1[i++];
16        while(j < nums2.length)
17             merger[k++] = nums2[j++];
18
19        int total = m + n ;
20
21        if(total % 2 == 0)
22            return (merger[total/2] + merger[total/2 - 1]) / 2.0;
23        else 
24            return (merger[total/2]); 
25
26
27
28        
29    }
30}