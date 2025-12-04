import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];

            if (d > dist[v]) continue;

            for (int[] n : graph.get(v)) {
                int nv = n[0];
                int nd = n[1];

                if (dist[nv] > dist[v] + nd) {
                    dist[nv] = dist[v] + nd;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }

        int toM = dist[V];
        int toG = dist[P];

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[P] = 0;

        pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{P, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];

            if (d > dist[v]) continue;

            for (int[] n : graph.get(v)) {
                int nv = n[0];
                int nd = n[1];

                if (dist[nv] > dist[v] + nd) {
                    dist[nv] = dist[v] + nd;
                    pq.add(new int[]{nv, dist[nv]});
                }
            }
        }

        int gtoM = dist[V];

        if (toM == toG + gtoM) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }
}

/*
조건
- 마산 갈 버스를 예약함.
- 지도보고 마산가는 가장 짧은 길 찾음.
- 건우가 떨궈짐.
- 가는 길에 건우를 주워야함.
- 근데 그냥 마산 가는 길이 더 짧으면 바로 가고, 같거나 멀면 건우 주우러 감.

요구
- 최단 경로 구하고 건우가 가는길에 건우 있으면 "SAVE HIM", "GOOD BYE"

풀이
- 다익
- 모든 노드에 대해 거리 구하고, 시작 -> 건우 + 건우 -> 마산 = 시작 -> 마산 이랑 동일하면 SAVE HIM
 */