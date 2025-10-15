import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        ArrayList<ArrayList<int[]>> rev = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            graph.get(start).add(new int[]{end, T});
            rev.get(end).add(new int[]{start, T});
        }

        int[] fromX = dijkstra(graph, X, N);
        int[] toX = dijkstra(rev, X, N);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, toX[i] + fromX[i]);
        }

        System.out.println(answer);
    }

    static int[] dijkstra(ArrayList<ArrayList<int[]>> g, int start, int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;

            for (int[] e : g.get(u)) {
                int v = e[0], w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}
