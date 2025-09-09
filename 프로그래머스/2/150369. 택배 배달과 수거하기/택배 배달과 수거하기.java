import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int del = 0;
        int pick = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            del += deliveries[i];
            pick += pickups[i];
            
            while (del > 0 || pick > 0) {
                del -= cap;
                pick -= cap;
                cnt++;
            }
            
            answer += (i + 1) * 2L * cnt;
        }
        
        return answer;
    }
}

/*
n개의 집에 배달.
cap만큼 실을 수 있음.
각 집에 배달하고 빈거 가져오기.

- 가장 먼집부터 처리
- 최소 이동 거리 구하기
*/