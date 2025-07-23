import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        dist[1] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int cost = cur[1];

            if (dist[now] < cost) {
                continue;
            }

            for (int[] next : graph.get(now)) {
                int nv = next[0];
                int nc = next[1];

                if (dist[nv] > dist[now] + nc) {
                    dist[nv] = dist[now] + nc;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }

        System.out.println(dist[N]);
    }
}
