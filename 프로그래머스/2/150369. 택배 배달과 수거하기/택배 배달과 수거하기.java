class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int del = 0;
        int pick = 0;
        for (int i = n - 1; i >= 0; i--) {
            del += deliveries[i];
            pick += pickups[i];
            int cnt = 0;
            
            while (del > 0 || pick > 0) {
                del -= cap;
                pick -= cap;
                cnt++;
            }
            
            answer += (i + 1) * cnt * 2;
        }
        return answer;
    }
}

/*
풀이
- 마지막 집부터 방문
- 해당 집 빌때까지 최대로 나르기
*/