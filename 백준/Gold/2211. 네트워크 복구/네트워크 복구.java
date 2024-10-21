import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dist, parent;
    static ArrayList<ArrayList<Edge>> list = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int b;
        int cost;
        public Edge(int b, int cost) {
            this.b = b;
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
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }
        dist[1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Edge(B, C));
            list.get(B).add(new Edge(A, C));
        }

        dijkstra();

        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            result.add(new int[]{i, parent[i]});
        }

        System.out.println(result.size());
        for (int[] edge : result) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int now = e.b;

            for (Edge next : list.get(now)) {
                if (dist[next.b] > dist[now] + next.cost) {
                    dist[next.b] = dist[now] + next.cost;
                    parent[next.b] = now;
                    pq.offer(new Edge(next.b, dist[next.b]));
                }
            }
        }
    }
}