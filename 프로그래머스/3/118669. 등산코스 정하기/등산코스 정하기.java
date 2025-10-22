import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {        
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int cost = path[2];
            
            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }
        
        boolean[] isSummit = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int gate : gates) {
            dist[gate] = 0;
            pq.add(new int[]{gate, 0});
        }
        
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int intensity = cur[1];
            
            if (intensity > dist[v]) continue;
            if (isSummit[v]) continue;
            
            for (int[] next : graph.get(v)) {
                int nv = next[0];
                int nc = next[1];
                int temp = Math.max(intensity, nc);
                
                if (dist[nv] > temp) {
                    dist[nv] = temp;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }
        
        Arrays.sort(summits);
        int bestSummit = -1;
        int bestIntensity = Integer.MAX_VALUE;
        for (int s : summits) {
            if (dist[s] < bestIntensity) {
                bestIntensity = dist[s];
                bestSummit = s;
            }
        }
        
        return new int[]{bestSummit, bestIntensity};
    }
}