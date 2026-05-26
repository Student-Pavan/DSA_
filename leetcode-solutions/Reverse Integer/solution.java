1class Solution {
2    public int reverse(int x) {
3        String str = String.valueOf(x);
4        long rem = 0;
5        long rev = 0;
6        long q = x;
7
8        if (str.length() == 1) {
9            return x;
10        }
11
12        if (x < 0) {
13            for (int i = 1; i < str.length(); i++) {
14                rem = q % 10;
15                rev = rev * 10 + rem;
16                q /= 10;
17            }
18        } else {
19            for (int i = 0; i < str.length(); i++) {
20                rem = q % 10;
21                rev = rev * 10 + rem;
22                q /= 10;
23            }
24        }
25
26        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
27            return 0;
28        }
29
30        return (int) rev;
31    }
32}
33