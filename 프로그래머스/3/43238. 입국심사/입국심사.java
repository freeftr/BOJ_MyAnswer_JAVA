import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long left = 0;
        long right = (long) times[times.length - 1] * n;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            
            for (int time : times) {
                count += mid/time;
            }
            
            if (count < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        
        return answer;
    }
}