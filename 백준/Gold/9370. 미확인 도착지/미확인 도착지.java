import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new int[]{b, d});
                graph.get(b).add(new int[]{a, d});
            }

            ArrayList<Integer> ends = new ArrayList<>();

            for (int i = 0; i < T; i++) {
                int x = Integer.parseInt(br.readLine());

                ends.add(x);
            }

            int[] distS = dijkstra(s, graph, N);
            int[] distG = dijkstra(g, graph, N);
            int[] distH = dijkstra(h, graph, N);

            Collections.sort(ends);

            for (int end : ends) {
                if (distS[end] == Integer.MAX_VALUE) continue;
                int gh = distS[g] + distG[h] + distH[end];
                int hg = distS[h] + distH[g] + distG[end];

                if (distS[end] == Math.min(gh, hg)) {
                    sb.append(end + " ");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static int[] dijkstra(int s, ArrayList<ArrayList<int[]>> graph, int N) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];

            if (dist[v] < d) continue;

            for (int[] next : graph.get(v)) {
                int nv = next[0];
                int nd = next[1];

                if (dist[nv] > dist[v] + nd) {
                    dist[nv] = dist[v] + nd;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }

        return dist;
    }
}

/*
조건
- 서커스 예술가 어디로 가는지 알기.
- s에서 출발해서 목적지 후보 중 하나로.
- g, h 교차로 사이에 있는 도로 정보 줌.

요구
- 가능한 목적지 후보들 오름차순 출력.

풀이
- 목적지들 중에서 g, h 사이 간선을 지나가는지 알아야 함.
- 경로를 역추적?
- 플래그도 같이 넣어서 확인?
 */
