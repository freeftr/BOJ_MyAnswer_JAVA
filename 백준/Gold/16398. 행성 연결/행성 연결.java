import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] graph;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int v;
        int w;
        int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.add(new Edge(i+1,j+1,Integer.parseInt(st.nextToken())));
            }
        }

        long sum = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(cnt==N-1){
                break;
            }

            if(find(e.v)==find(e.w)){
                continue;
            }

            union(e.v, e.w);
            sum += e.cost;
            cnt++;
        }

        System.out.println(sum);
    }

    static int find(int v){
        if(v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int v, int w){
        v = find(v);
        w = find(w);
        if (v>w) {
            parent[w] = v;
        }
        else {
            parent[v] = w;
        }
    }
}