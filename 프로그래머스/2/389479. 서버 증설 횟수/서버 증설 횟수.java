import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int time = 0;
        
        for (int player : players) {
            while (!pq.isEmpty() && pq.peek() <= time) pq.poll();
            
            int currentCapacity = m * pq.size() + m;
            
            // System.out.println(time + " " + player + " " + pq.size() + " " + currentCapacity);
            
            if (player < currentCapacity) {
                time++;
                continue;
            }
            
            while (player >= currentCapacity) {
                answer++;
                currentCapacity += m;
                // System.out.println("new Server " + (time + k));
                pq.add(time + k);
            }
            
            time++;
        }
        
        return answer;
    }
}

/*
조건
- 같은 시간대 이용 수가 m명 늘어날때마다 서버 1대 증설
- k 시간 동안 운영 후 반납

요구
- 하룻동안 최소 서버 증설 횟수 구하기

풀이
- 
*/