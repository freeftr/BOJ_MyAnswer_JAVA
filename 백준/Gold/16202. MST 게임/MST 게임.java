import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int v, w, cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, i));
        }

        for (int i = 0; i < K; i++) {
            if (edges.size() < N - 1) {
                sb.append("0 ");
                continue;
            }

            parents = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                parents[j] = j;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
            int cnt = 0, sum = 0;

            while (!pq.isEmpty()) {
                Edge e = pq.poll();

                if (find(e.v) != find(e.w)) {
                    union(e.v, e.w);
                    cnt++;
                    sum += e.cost;
                    if (cnt == N - 1) break;
                }
            }

            if (cnt == N - 1) {
                sb.append(sum).append(" ");
            } else {
                sb.append("0 ");
            }

            if (!edges.isEmpty()) edges.remove(0);
        }

        System.out.println(sb);
    }

    static int find(int v) {
        if (v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    static void union(int v, int w) {
        v = find(v);
        w = find(w);
        if (v != w) {
            parents[w] = v;
        }
    }
}
