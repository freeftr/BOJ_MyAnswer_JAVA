import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        int now = toNum(today);
        
        HashMap<String, Integer> termMap = new HashMap<>();
        
        for (String term : terms) {
            String[] temp = term.split(" ");
            
            termMap.put(temp[0], Integer.parseInt(temp[1]));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        int idx = 1;
        for (String p : privacies) {
            String[] temp = p.split(" ");
            int start = toNum(temp[0]);
            int end = start + termMap.get(temp[1]) * 28;
            
            if (end <= now) result.add(idx);
            
            // System.out.println(now + " " + end);
            
            idx++;
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    static int toNum(String day) {
        String[] temp = day.split("\\.");
        
        int result = 0;
        
        result += Integer.parseInt(temp[2]);
        result += Integer.parseInt(temp[1]) * 28;
        result += Integer.parseInt(temp[0]) * 12 * 28;
        
        return result;
    }
}

/*
조건
- 개인정보 n개
- 약관은 여러 개 있음. 개인정보 - 약관
- 유효기간 전까지만 보관 가능. -> 달 기준
- 모든 달은 28일까지

요구
- 파기해야할 개인정보 구하기

풀이
- 그냥 구현
*/