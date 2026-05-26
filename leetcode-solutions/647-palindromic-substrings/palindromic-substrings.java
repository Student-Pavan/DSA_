class Solution {
    public int countSubstrings(String s) {
        int count = 0;

        for(int i = 0 ; i < s.length() ; i++){
            count += expand(i,i,s);
            count += expand(i,i+1,s); 
        }

        return count;
    }

    private int expand(int left, int right, String s){
        int count = 0;

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            count++;
            left --;
            right ++;

        }
        return count;
    }
}