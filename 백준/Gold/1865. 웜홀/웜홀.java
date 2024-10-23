import java.io.*;
import java.util.*;

public class Main {
    static int TC, N, M, W;

    static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();
            List<Integer> wormholes = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
                wormholes.add(S);
            }

            boolean hasNegativeCycle = false;
            for (int start : wormholes) {
                if (bellmanFord(start, N, edges)) {
                    hasNegativeCycle = true;
                    break;
                }
            }

            if (hasNegativeCycle) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean bellmanFord(int start, int n, List<Edge> edges) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 1; i < n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.cost < dist[edge.to]) {
                return true;
            }
        }

        return false;
    }
}