import java.io.*;
import java.util.*;

public class Main {
    static int N, M, t;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        parent = new int[N+1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        Collections.sort(edges);

        int cnt = 0;
        int sum = 0;
        for (Edge edge : edges) {
            if (find(edge.to) != find(edge.from)) {
                union(edge.to, edge.from);
                sum += edge.cost + cnt * t;
                cnt++;
            }
            if (cnt == N - 1) break;
        }

        System.out.println(sum);
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
        public int compareTo (Edge o) {
            return this.cost - o.cost;
        }
    }

    static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a!=b) {
            parent[a] = b;
        }
    }
}
