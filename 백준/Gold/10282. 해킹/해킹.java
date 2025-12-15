import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new int[]{a, s});
            }

            int[] dist = new int[n + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[c] = 0;
            pq.add(new int[]{c, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int V = cur[0];
                int D = cur[1];

                if (dist[V] < D) continue;

                for (int[] N : graph.get(V)) {
                    int nv = N[0];
                    int nd = N[1];

                    if (dist[nv] > dist[V] + nd) {
                        dist[nv] = dist[V] + nd;
                        pq.add(new int[]{nv, dist[nv]});
                    }
                }
            }

            int cnt = 0;
            int max = 0;

            for (int i = 1; i < n + 1; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    max = Math.max(max, dist[i]);
                }
            }

            sb.append(cnt + " " + max).append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- a가 b에 의존 = b -> a

요구
- 몇대의 컴퓨터가 감염되고, 얼마나 걸리는지
 */