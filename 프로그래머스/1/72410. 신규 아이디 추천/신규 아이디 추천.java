class Solution {
    public String solution(String new_id) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        // 1. 소문자화
        new_id = new_id.toLowerCase();
        
        // 2. 알파벳 소문자, 숫자, -, _, . 확인
        for (int i = 0; i < new_id.length(); i++) {
            char cur = new_id.charAt(i);
            
            if (Character.isDigit(cur)) {
                sb.append(cur);
            }
            
            if (Character.isLetter(cur)) {
                sb.append(cur);
            }
            
            if (cur == '-' || cur == '.' || cur == '_') {
                sb.append(cur);
            }
        }
        
        // 3. 연속된 점 제거.
        String temp = sb.toString().replaceAll("\\.+", ".");
        sb = new StringBuilder(temp);

        // 4. 양 끝 마침표 제거.
        if (sb.charAt(0) == '.') sb.deleteCharAt(0);
        if (!sb.toString().isEmpty() && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        
        // 5. 비었으면 a 넣기
        if (sb.toString().isEmpty()) sb.append("a");
        
        // 6. 15자 제한.
        if (sb.length() > 15) {
            String cutted = sb.toString().substring(0, 15);
            sb = new StringBuilder(cutted);
        }
        
        if (sb.charAt(0) == '.') sb.deleteCharAt(0);
        if (!sb.toString().isEmpty() && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        
        // 7. 2자 이하면 늘리기
        if (sb.length() <= 2) {
            char last = sb.charAt(sb.length() - 1);
            while (sb.length() != 3) {
                sb.append(last);
            }
        }
        
        // System.out.println(sb.toString());
        
        return sb.toString();
    }
}

/*
조건
- 알파벳 소문자, 숫자, -, _, . 만 사용 가능.
- .는 처음과 끝 불가능. 연속으로 불가능.
- 소문자로 치환.
- 사용가능한거빼고 제거.
- 연속된 . 하나로 치환.
- 처음과 끝에 있는 . 제거.
- 빈문자열에 a 넣기
- 15자로 자르기.
- 마침표 다시.
- 길이 2이하면 마지막 문자를 3까지 반복.
*/