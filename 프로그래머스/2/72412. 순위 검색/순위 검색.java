import java.util.*;

class Solution {
    
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (String inf : info) {
            String[] s = inf.split(" ");
            dfs(0, s, "");
        }

        for (ArrayList<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        int idx = 0;
        for (String s : query) {
            String temp = s.replace(" and", "");
            String[] elements = temp.split(" ");
            int target = Integer.parseInt(elements[elements.length - 1]);
            String key = "";
            
            for (int i = 0; i < elements.length - 1; i++) {
                key += elements[i];
            }
            
            ArrayList<Integer> scores = map.get(key);
            
            int cnt = 0;
            if (scores == null) {
                answer[idx++] = 0;
            } else {
                int left = 0;
                int right = scores.size();
                
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (scores.get(mid) < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                answer[idx++] = scores.size() - left;
            }
        }
        
        return answer;
    }
    
    static void dfs(int depth, String[] info, String cur) {
        if (depth == 4) {
            map.computeIfAbsent(cur, k -> new ArrayList<Integer>()).add(Integer.parseInt(info[4]));
            return;
        }
        
        dfs(depth + 1, info, cur + info[depth]);
        dfs(depth + 1, info, cur + "-");
    }
}
