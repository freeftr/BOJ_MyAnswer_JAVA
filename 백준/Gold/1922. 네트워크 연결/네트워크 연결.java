import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int [N+1];

        for (int i = 0; i < N+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a,b,c));
        }

        Collections.sort(edges);

        int sum = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                cnt++;
                sum+=e.cost;
            }

            if (cnt == N-1) {
                break;
            }
        }

        System.out.println(sum);
    }

    static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a!=b){
            parent[b] = a;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
