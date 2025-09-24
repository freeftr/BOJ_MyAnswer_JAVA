import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length(); i++) {
            answer = Math.min(answer, convert(s, i));
        }
        return answer;
    }
    
    static int convert(String s, int length) {
        ArrayList<String> list = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i+= length) {
            if (i + length > s.length()) {
                list.add(s.substring(i));
                break;
            }
            list.add(s.substring(i, i + length));
        }
        
        if (list.size() == 1) return list.get(0).length() ;
        
        String prev = list.get(0);
        String result = "";
        int cnt = 1;
        for (int i = 1; i < list.size(); i++) {
            String now = list.get(i);
            if (now.equals(prev)) {
                cnt++;
            } else {
                if (cnt > 1) {
                    String temp = cnt + prev;
                    result += temp;
                    cnt = 1;
                    prev = now;
                } else {
                    result += prev;
                    prev = now;
                }
            }
        }
        result += cnt == 1 ? list.get(list.size() - 1) : cnt + list.get(list.size() - 1);
    
        return result.length();
    }
}