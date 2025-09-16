import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        for (String rep : report) {
            String reporter = rep.split(" ")[0];
            String reported = rep.split(" ")[1];
            reportMap.computeIfAbsent(reported, a -> new HashSet<>()).add(reporter);
        }
        
        for (String key : reportMap.keySet()) {
            HashSet<String> reporters = reportMap.get(key);
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    result.merge(reporter, 1, (a, b) -> a + b);
                }
            }
        }
        
        for (int i = 0; i < answer.length; i++) {
            String key = id_list[i];
            if (result.get(key) == null) continue;
            answer[i] = result.get(key);
            System.out.println(i + " " + key + " " + answer[i]);
        }
        return answer;
    }
}

/*
한번에 한명 유저 신고
k번 이상일 신고한 모든 유저에게 메일
*/