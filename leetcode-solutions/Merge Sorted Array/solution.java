1class Solution {
2    public void merge(int[] nums1, int m, int[] nums2, int n) {
3        for(int i = 0 ; i < m ; i++){
4            if(nums1[i] != 0 )
5                nums1[i] = nums1[i];
6        }
7        int c = 0 ; 
8        for(int j = m ; j < m+n; j++){
9            if(nums2[c] != 0 )
10                nums1[j] = nums2[c];
11            c++;  
12        }
13        Arrays.sort(nums1);
14    }
15}