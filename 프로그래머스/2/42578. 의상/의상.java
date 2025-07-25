import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            map.computeIfAbsent(cloth[1], k -> new ArrayList<>()).add(cloth[0]);
        }
        
        for (String key : map.keySet()) {
            int size = map.get(key).size();
            
            answer *= size + 1;
        }
        
        answer--;
        
        return answer;
    }
}