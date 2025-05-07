import java.util.*;

class Solution {
    
    static int[] parent;
    
    static class Edge implements Comparable<Edge>{
        int from, to, cost;
        
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        ArrayList<Edge> list = new ArrayList<>();
        parent = new int[n];
        
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] cost : costs){
            list.add(new Edge(cost[0], cost[1], cost[2]));
            list.add(new Edge(cost[1], cost[0], cost[2]));
        }
        
        Collections.sort(list);
        
        for (Edge edge : list){
            if (find(edge.from) != find(edge.to)){
                union(edge.from, edge.to);
                answer += edge.cost;
            }
        }
        
        return answer;
    }
    
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
    
    static int find(int v){
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }
}
