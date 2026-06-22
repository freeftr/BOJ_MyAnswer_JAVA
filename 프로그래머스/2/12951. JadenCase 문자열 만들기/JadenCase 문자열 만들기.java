import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                isFirst = true;
                sb.append(' ');
                continue;
            }
            
            if (isFirst) {
                isFirst = false;
                
                if (Character.isLetter(s.charAt(i))) {
                    sb.append(Character.toUpperCase(s.charAt(i)));
                } else {
                    sb.append(s.charAt(i));
                }
            } else {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        
        answer = sb.toString();
        
        return answer;
    }
}

/*
모든 단어의 첫문자가 대문자, 나머지는 소문자

풀이
1. 단어 별로 분할
2. 단어 별로 맨 앞글자 검사
3. 단어 별로 나머지 문자 소문자화
4. 합치기
*/