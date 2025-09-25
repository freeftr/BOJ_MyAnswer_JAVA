import java.util.*;
class Solution {
    
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (String in : info) {
            String[] data = in.split(" ");
            dfs(0, data, "");
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
    
    static void dfs(int depth, String[] data, String key) {
        if (depth == 4) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(Integer.parseInt(data[4]));
            return;
        }
        
        dfs(depth + 1, data, key + data[depth]);
        dfs(depth + 1, data, key + "-");
    }
}