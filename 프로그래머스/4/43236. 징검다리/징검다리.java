import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (check(mid, rocks, n, distance)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    static boolean check(int target, int[] rocks, int n, int distance) {
        int cnt = 0;
        int idx = 0;
        int prev = 0;
        
        for (int i = 0; i < rocks.length; i++) {
            int dist = rocks[i] - prev;
            
            if (dist < target) {
                cnt++;
            } else {
                prev = rocks[i];
            }
            
            // System.out.println("mid:" + target + " dist:" + dist + " prev:" + prev + " cnt:" + cnt);
        }
        
        if (distance - prev < target) {
            cnt++;
        }
        
        return cnt <= n;
    }
}