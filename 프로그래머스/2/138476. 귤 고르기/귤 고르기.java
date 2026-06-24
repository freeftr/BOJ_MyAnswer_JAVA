import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        
        for (int t : tangerine) {
            cnt.merge(t, 1, Integer::sum);
        }
        
        List<Integer> sizes = new ArrayList<>(cnt.values());
        
        sizes.sort(Collections.reverseOrder());
        
        int sum = 0;
        
        for (int i : sizes) {
            sum += i;
            answer++;
            
            if (sum >= k) return answer;
        }
        
        return answer;
    }
}