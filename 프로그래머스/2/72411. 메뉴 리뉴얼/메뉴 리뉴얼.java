import java.util.*;
class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> result = new ArrayList<>();
        
        for (int menu : course) {
            map.clear();

            for (String order : orders) {
                String[] ordered = order.split("");
                Arrays.sort(ordered);
                dfs(ordered, 0, "", menu);
            }
            
            int max = 0;
            for (int v : map.values()) max = Math.max(max, v);
            if (max >= 2) {
                for (Map.Entry<String, Integer> e : map.entrySet()) {
                    if (e.getValue() == max) result.add(e.getKey());
                }
            }
        }
        
        Collections.sort(result);
        answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
    
    static void dfs(String[] ordered, int depth, String cur, int target) {
        if (cur.length() == target) {
            map.merge(cur, 1, (a, b) -> a + b);
            return;
        }
        if (depth >= ordered.length) return;
        
        for (int i = depth; i < ordered.length; i++) {
            String temp = cur;
            temp += ordered[i];
            dfs(ordered, i + 1, temp, target);
        }
    }
}
