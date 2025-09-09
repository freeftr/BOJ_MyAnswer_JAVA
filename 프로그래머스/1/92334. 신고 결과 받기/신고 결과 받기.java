import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, ArrayList<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> idMap = new HashMap<>();
    
        int idx = 0;
        for (String id : id_list) {
            idMap.put(id, idx++);
        }
        
        for (String rep : report) {
            String reporter = rep.split(" ")[0];
            String reported = rep.split(" ")[1];
            
            reportMap.computeIfAbsent(reported, a -> new ArrayList<>()).add(reporter);
        }
        
        for (String key : reportMap.keySet()) {
            ArrayList<String> temp = reportMap.get(key);
            Set<String> reporters = new HashSet<>();
            reporters.addAll(temp);
            
            if (reporters == null) continue;
            
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    answer[idMap.get(reporter)]++;   
                }
            }
        }
        
        return answer;
    }
}

/*
- 동일한 유저에 대한 신고는 1회
- k번 이상 시 정지 + 신고한 사람들한테 메일
*/