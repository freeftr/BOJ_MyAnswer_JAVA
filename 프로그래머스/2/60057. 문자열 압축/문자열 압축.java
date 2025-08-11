class Solution {
    public int solution(String s) {
        int length = s.length();
        int answer = length;
        
        if (length == 1) {
            return 1;
        }
        
        for (int i = 1; i <= length / 2; i++) {
            int idx = 0;
            String base = s.substring(idx, idx + i);
            String result = "";
            
            idx += i;
            
            int cnt = 1;
            while (idx + i <= length) {
                String pattern = s.substring(idx, idx + i);
                if (pattern.equals(base)) {
                    cnt++;
                } else {
                    if (cnt > 1) result += cnt;
                    result += base;
                    base = pattern;
                    cnt = 1;
                }
                idx += i;
            }

            if (cnt > 1) result += cnt;
            result += base;

            if (idx < length) result += s.substring(idx);

            answer = Math.min(answer, result.length());
        }
        
        return answer;
    }
}