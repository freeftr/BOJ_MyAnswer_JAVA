import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] roa : road) {
            int a = roa[0];
            int b = roa[1];
            int cost = roa[2];
            
            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }
        int[] dist = new int[N+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            int now = cur[0];
            int dis = cur[1];
            
            if (dis > dist[now]) continue;
            
            for (int[] nv : graph.get(now)) {
                int next = nv[0];
                int nextDis = nv[1];
                
                if (dist[next] > dist[now] + nextDis) {
                    dist[next] = dist[now] + nextDis;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}