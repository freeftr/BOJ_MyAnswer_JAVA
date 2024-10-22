import java.io.*;
import java.util.*;

class Solution {
    
    static int N;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static int[] dist;
    static boolean[] visited;
    
    static class Edge implements Comparable<Edge> {
        int v;
        int cost;
        
        Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        N = n;
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            graph.get(road[0]).add(new Edge(road[1], 1));
            graph.get(road[1]).add(new Edge(road[0], 1));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(destination, 0));
        
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int now = e.v;
            
            if (visited[now]) continue;
            visited[now] = true;
            
            for (Edge next : graph.get(now)) {
                if (dist[next.v] > dist[now] + next.cost) {
                    dist[next.v] = dist[now] + next.cost;
                    pq.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }
        
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = dist[sources[i]];
            }
        }
        
        return answer;
    }
}
