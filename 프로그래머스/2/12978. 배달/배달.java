import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 1;
        
        // 간선 정보 전처리
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[] dist = new int[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            graph.get(b).add(new int[]{a, c});
            graph.get(a).add(new int[]{b, c});
        }
        
        // 다익스트라
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];
            
            if (d > dist[v]) continue;
            
            for (int[] n : graph.get(v)) {
                int nv = n[0];
                int nd = n[1];

                if (dist[nv] > dist[v] + nd) {
                    dist[nv] = dist[v] + nd;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }
        
        // K 이하 확인
        for (int i = 2; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }
        
        return answer;
    }
}

/*
조건
- N개의 마을로 이루어진 나라.
- 양방향
- 도로별 가중치 있음.
- 가중치 합 제한 = K
- 음식점은 1번 마을에 있음.

요구
- 음식 주문을 받을 수 있는 마을 의 개수 구하기

풀이
- 1번마을에서 다익스트라 돌리기
*/