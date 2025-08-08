import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[n+1];
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int[] info : edge) {
            int a = info[0];
            int b = info[1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        dist[1] = 0;
        pq.add(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int cd = cur[1];
            
            if (cd > dist[v]) continue;
            
            for (int nv : graph.get(v)) {
                if (dist[nv] > dist[v] + 1) {
                    dist[nv] = dist[v] + 1;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }
        
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                if (dist[i] > max) max = dist[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) answer++;
        }

        
        return answer;
    }
}