class Solution {
    public String solution(String s) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        char prev = ' ';
        
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            
            if (now == ' ') {
                sb.append(" ");
            } else if (prev == ' ' && now != ' ') {
                if (Character.isLetter(now)) {
                    sb.append(Character.toUpperCase(now));
                } else {
                    sb.append(now);
                }
            } else {
               sb.append(Character.toLowerCase(now)); 
            }
            
            prev = now;
        }
        
        answer = sb.toString();
        
        return answer;
    }
}

/*
조건
- JadenCase = 첫 문자 대문자, 나머지 소문자.

요구
- s를 JadenCase로 만들어라.

풀이
- 문자열 잘라서 해 그냥.
*/