class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character , Integer> map = new HashMap<>();
        List<Integer> ls = new ArrayList<>();
        for(int i = 0 ; i < p.length() ;i++){
            char ch = p.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }


        int size = p.length();
        int count = map.size();
        int left = 0;

        for(int i = 0 ; i < s.length() ;i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.getOrDefault(ch,0)-1);

                if(map.get(ch) == 0){
                    count--;
                }
            }

            if(i - left + 1 == size){
                if(count == 0){
                    ls.add(left);
                }

                char leftchar = s.charAt(left);

                if(map.containsKey(leftchar)){
                    map.put(leftchar,map.getOrDefault(leftchar,0)+1);

                    if(map.get(leftchar) == 1){
                        count++;
                    }
                }
                left++;
            }
        }

        return ls;


    }
}