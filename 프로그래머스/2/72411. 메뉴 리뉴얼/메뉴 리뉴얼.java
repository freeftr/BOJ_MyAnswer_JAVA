import java.util.*;

class Solution {
    
    static HashMap<String, Integer> map = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        
        for (int len : course) {
            map.clear();
            for (String s : orders) {
                char[] order = s.toCharArray();
                Arrays.sort(order);
                dfs(0, "", order, len);
            }
            
            int max = 2;
            for (int value : map.values()) {
                if (value > max) {
                    max = value;
                }
            }
            
            for (String key : map.keySet()) {
                if (map.get(key) == max) {
                    result.add(key);
                }
            }
        }
        
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    static void dfs(int depth, String course, char[] order, int len) {
        if (course.length() == len) {
            map.put(course, map.getOrDefault(course, 0) + 1);
            return;
        }
        if (depth == order.length) {
            return;
        }
        
        dfs(depth + 1, course + order[depth], order, len);
        dfs(depth + 1, course, order, len);
    }
}
