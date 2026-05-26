1class Solution {
2    public int subarraySum(int[] arr, int k) {
3        HashMap<Integer, Integer> map = new HashMap<>();
4
5        map.put(0, 1);
6        int prefixsum = 0;
7        int count = 0;
8        for (int i = 0; i < arr.length; i++) {
9            prefixsum += arr[i];
10
11            if (map.containsKey(prefixsum - k)) {
12                count += map.get(prefixsum - k);
13            }
14            map.put(prefixsum, map.getOrDefault(prefixsum, 0) + 1);
15        }
16        return count;
17    }
18}