import java.util.*;
class Solution {
    static HashMap<String, ArrayList<Integer>> result = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (String f : info) {
            String[] temp = f.split(" ");
            dfs(0, temp, new StringBuilder());
        }
        
        int idx = 0;
        
        for (String key : result.keySet()) {
            Collections.sort(result.get(key));
        }
        
        for (String q : query) {
            String a = q.replaceAll("and ", "");
            String[] temp = a.split(" ");
            StringBuilder key = new StringBuilder();
            
            for (int i = 0; i < temp.length - 1; i++) key.append(temp[i]);
            
            int target = Integer.parseInt(temp[4]);
            
            ArrayList<Integer> candidates = result.get(key.toString());
            
            if (candidates == null) {
                answer[idx++] = 0;
                continue;
            }
            
            int left = 0;
            int right = candidates.size();
            
            while (left < right) {
                int mid = (left + right) / 2;
                
                if (candidates.get(mid) >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            
            answer[idx++] = candidates.size() - left;
        }
        return answer;
    }
    
    public void dfs(int depth, String[] info, StringBuilder cur) {
        if (depth == 4) {
            result.computeIfAbsent(cur.toString(), k -> new ArrayList<>()).add(Integer.parseInt(info[4]));
            return;
        }
        
        int len = cur.length();
        
        cur.append(info[depth]);
        dfs(depth + 1, info, cur);
        cur.setLength(len);
        
        cur.append("-");
        dfs(depth + 1, info, cur);
        cur.setLength(len);
    }
}

/*
조건
- 언어, 직군, 경력, 소울푸드 네가지 항목 + 코테 점수

요구
- 쿼리에 맞게 리턴해라

풀이
- 각 항목을 다 잘라서 만들수 있는 스트링을 키로 점수 다 때려박음.
*/