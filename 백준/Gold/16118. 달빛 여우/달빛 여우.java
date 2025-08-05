import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken()) * 2;

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
            int curDist = cur[1];

            if (dist[now] < curDist) continue;

            for (int[] next : graph.get(now)) {
                if (dist[next[0]] > dist[now] + next[1]) {
                    dist[next[0]] = dist[now] + next[1];
                    pq.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq2.add(new int[]{1, 0, 0});

        int[][] dist2 = new int[N+1][2];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist2[i], Integer.MAX_VALUE);
        }

        dist2[1][0] = 0;

        while (!pq2.isEmpty()) {
            int[] cur = pq2.poll();
            int now = cur[0];
            int curDist = cur[1];
            int run = cur[2];

            if (dist2[now][run] < curDist) continue;

            for (int[] next : graph.get(now)) {
                int nv = next[0];
                int cost = next[1];
                int nd = 0;
                int nr = 1 - run;

                if (run == 0) {
                    nd = curDist + cost / 2;
                } else {
                    nd = curDist + cost * 2;
                }

                if (dist2[nv][nr] > nd) {
                    dist2[nv][nr] = nd;
                    pq2.add(new int[]{nv, nd, nr});
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] < Math.min(dist2[i][0], dist2[i][1])) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
