import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        int length = privacies.length;
        HashMap<String, Integer> termMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        for (String term : terms) {
            String type = term.split(" ")[0];
            int due = Integer.parseInt(term.split(" ")[1]);
            
            termMap.put(type, due);
        }
        
        
        String[] t = today.split("\\.");
        int tYear = Integer.parseInt(t[0]);
        int tMonth = Integer.parseInt(t[1]);
        int tDay = Integer.parseInt(t[2]);
            
        int tm = tYear * 12 * 28 + tMonth * 28 + tDay;
        
        for (int i = 0; i < length; i++) {
            String[] s1 = privacies[i].split(" ");
            String[] s = s1[0].split("\\.");
            int year = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);
            int day = Integer.parseInt(s[2]);
            String type = s1[1];
            int due = termMap.get(type);
            
            int m = year * 12 * 28 + month * 28 + day + due * 28 - 1;
            
            if (tm > m) {
                result.add(i + 1);
            }
        }
        
        Collections.sort(result);
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}

/*
n개의 개인정보
약관 유효기간.
- 파기 해야할 개인정보 번호 구하기.
*/