import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> a.length() - b.length());
        s = s.substring(2, s.length() - 2);
        String[] tuples = s.split("\\},\\{");
        
        for (String tuple : tuples) {
            pq.add(tuple);
        }
        ArrayList<String> result = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            String cur = pq.poll();
            String[] temp = cur.split(",");
            
            for (String t : temp) {
                if (result.contains(t)) continue;
                result.add(t);
            }
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = Integer.parseInt(result.get(i));
        }
        
        return answer;
    }
}

/*
- 순서가 다르면 다른 튜플
*/