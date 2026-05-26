1class Solution {
2    public int[] maxSlidingWindow(int[] nums, int k) {
3        int n = nums.length;
4
5        int[] result =  new int[n - k + 1];
6        Deque<Integer> deque = new LinkedList<>();
7
8        for(int right = 0 ; right < n ; right++){
9            while(!deque.isEmpty() && deque.peekFirst() <= right - k){
10                deque.pollFirst();
11            }
12            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[right]){
13                deque.pollLast();
14            }
15            deque.addLast(right);
16            if(right >= k - 1){
17                result[right - k + 1] = nums[deque.peekFirst()];
18            }
19        }
20        return result;
21    }
22}