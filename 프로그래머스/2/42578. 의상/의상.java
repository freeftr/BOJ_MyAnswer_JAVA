import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            map.merge(cloth[1], 1, Integer::sum);
        }
        
        for (int k : map.values()) {
            answer *= k + 1;
        }
        
        return answer - 1;
    }
}