1class Solution {
2    public int sumOfUnique(int[] nums) {
3
4        int max = 0;
5        for (int n : nums)
6            max = Math.max(max, n);
7
8        int[] freq = new int[max + 1];
9
10       
11        for (int count : nums)
12            freq[count]++;
13
14       
15        int sum = 0;
16        for (int i = 0; i < freq.length; i++) {
17            if (freq[i] == 1)
18                sum += i;
19        }
20
21        return sum;
22    }
23}
24