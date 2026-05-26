class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;

        for(int i = 0; i < s.length() ;i++){
            int len1 = expand(s, i, i); // odd len check
            int len2 = expand(s, i, i+1); // even len check

            int maxlen = Math.max(len1,len2);

            if(maxlen > end - start){
                start = i - ((maxlen - 1 )/2);
                end = i + (maxlen/2);
            }
        }

        return s.substring(start, end+1);
    }


    private int expand(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right) ){
            left--;
            right++;
        }
        return right - left - 1;
    }
}