import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] result = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            result[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }

        for (int i = 1; i <= n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            int[] dist = new int[n + 1];

            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[i] = 0;

            pq.add(new int[]{i, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int v = cur[0];
                int d = cur[1];
                if (dist[v] < d) continue;

                for (int[] n : graph.get(v)) {
                    int nv = n[0];
                    int nd = n[1];

                    if (dist[nv] > dist[v] + nd) {
                        dist[nv] = dist[v] + nd;
                        result[nv][i] = v;
                        pq.add(new int[]{nv, dist[nv]});
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(result[i][j] == 0 ? "- " : result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 노드 = 집하장
- 경로표 = 한 집하장에서 다른 집하장으로 최단 경로로 갈시 가장 먼저 거쳐야 하는 집하장.

요구
- 경로표 구하기

풀이
1 -> 4

1 -> 3 -> 4
 */