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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[N + 1];
        int[] prev = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        pq.add(new int[]{1, 0});

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
                    prev[nv] = v;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (i == 0) continue;
            cnt++;
            sb.append(i + " " + prev[i] + "\n");
        }

        System.out.println(cnt);
        System.out.println(sb.toString());
    }
}

/*
조건
- 컴퓨터들이 서로 연결되어 있음.
- 간선에 성능 존재.
- 슈퍼컴퓨터는 1 -> 얘가 복구함.
- 최소 개수 회선 선택.
- 보안 패킷 걸리는 시간 구하기.

요구
- 복구한 회선 표시

풀이
- 다익하면서 그냥 넣으면 댐.
 */