class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            answer = Math.max(answer, check(s, i, true));
            answer= Math.max(answer, check(s, i, false));
        }
        
        return answer;
    }
    
    static int check(String s, int center, boolean even) {
        int left = center;
        int right = center;
        int max = 0;
        
        if (even) right++;
        
        while (true) {
            if (left < 0 || right >= s.length()) break;
            if (s.charAt(left) != s.charAt(right)) break;
            
            int len = right - left + 1;
            
            max = Math.max(len, max);
            
            left--;
            right++;
        }
        
        return max;
    }
}