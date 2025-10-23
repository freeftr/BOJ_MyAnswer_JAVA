import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<String> alphabets = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        alphabets.add("1");
        
        // 1. 사전 초기화 인덱스 1부터 시작.
        for (int i = 0; i < 26; i++) {
            String c = (char) (65 + i) + "";
            alphabets.add(c);
        }
        
        int len = msg.length();
        StringBuilder sb = new StringBuilder();
        sb.append(msg.charAt(0));
        
        int idx = 1;
        while (idx < len) {
            char c = msg.charAt(idx);
            String w = sb.toString();
            String temp = w + c;
            
            // 사전에 있는 경우.
            if (alphabets.contains(temp)) {
                idx++;
                sb.append(c);
            }
            
            // 사전에 없는 경우.
            if (!alphabets.contains(temp)) {
                result.add(alphabets.indexOf(w));
                alphabets.add(temp);
                sb = new StringBuilder();
                sb.append(c);
                idx++;
            }
            
        }
        
        result.add(alphabets.indexOf(sb.toString()));
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}

/*
조건
- LZW로 압축함.
- 알파벳 다 넣기.
- 현재 입력과 일치하는 w 찾기.
- w 인덱스 출력하고 입력 버퍼 비우기.
- 남아있는 c를 w + c로 만들어서 사전 등록.
- 대문자만 처리.
*/