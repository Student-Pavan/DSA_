1class Solution {
2    public int totalFruit(int[] fruits) {
3        HashMap<Integer, Integer> map = new HashMap<>();
4
5        int left = 0;
6        int count = 0;
7
8        for(int right = 0 ; right < fruits.length ; right++){
9            int curr = fruits[right];
10            map.put(curr , map.getOrDefault(curr,0)+1);
11
12            while(map.size() > 2){
13                int leftf = fruits[left];
14                map.put(leftf , map.getOrDefault(leftf,0)-1);
15                if(map.get(leftf) == 0){
16                    map.remove(leftf);
17                }
18                left++;
19            }
20            count = Math.max(count,right - left + 1);
21        }
22        return count;
23    }
24    
25}