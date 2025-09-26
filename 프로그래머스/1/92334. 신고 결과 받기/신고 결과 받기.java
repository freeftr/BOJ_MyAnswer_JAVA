import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        HashMap<String, HashSet<String>> reportedMap = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        
        for (String rep : report) {
            String reporter = rep.split(" ")[0];
            String reported = rep.split(" ")[1];
            
            reportedMap.computeIfAbsent(reported, a -> new HashSet<>()).add(reporter);
        }
        
        for (String reported : reportedMap.keySet()) {
            HashSet<String> reporters = reportedMap.get(reported);
            if (reporters == null) continue;
            
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    result.merge(reporter, 1, (a, b) -> a + b);
                }
            }
        }
        
        answer = new int[id_list.length];
        for (int i = 0; i < answer.length; i++) {
            if (result.get(id_list[i]) == null) continue;
            answer[i] = result.get(id_list[i]);
        }
        return answer;
    }
}

/*
조건
- k번이상이면 정지
- 정지 시 신고자에게 알림.

요구
- 몇번 신고성공하는지 알리기.
*/