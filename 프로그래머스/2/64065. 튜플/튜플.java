import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        s = s.substring(2, s.length() - 2);
        String[] temp = s.split("\\},\\{");
        
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(temp, (a, b) -> a.length() - b.length());
        
        for (String t : temp) {
            String[] info = t.split(",");
            for (String i : info) {
                int cur = Integer.parseInt(i);
                if (!result.contains(cur)) result.add(cur);
            }
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
}

/*
조건
- 중복된 원소 가능
- 순서가 다르면 다른 튜플

요구
- s가 표현하는 튜플을 반환
*/