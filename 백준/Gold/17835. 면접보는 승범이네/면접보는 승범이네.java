import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static ArrayList<ArrayList<long[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            // 역방향 그래프
            graph.get(V).add(new long[]{U, C});
        }

        ArrayList<Integer> interview = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            interview.add(Integer.parseInt(st.nextToken()));
        }

        int city = -1;
        long answer = -1;

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        for (int start : interview) {
            dist[start] = 0;
            pq.add(new long[]{start, 0});
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            if (dist[(int) cur[0]] < cur[1]) continue;

            for (long[] n : graph.get((int) cur[0])) {
                if (dist[(int) n[0]] > dist[(int) cur[0]] + n[1]) {
                    dist[(int) n[0]] = dist[(int) cur[0]] + n[1];
                    pq.add(new long[]{n[0], dist[(int) n[0]]});
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] == Long.MAX_VALUE) continue;
            if (dist[i] > answer) {
                answer = dist[i];
                city = i;
            }
        }

        System.out.println(city);
        System.out.println(answer);
    }
}

/*
조건
- 단방향
- K개의 면접장
- 본인 도시에서 출발해서 가장 가까운 면접장으로 간다.

요구
- 면접장까지 가장 먼 도시와 거리 구하기.

풀이
- 면접장에서 도시까지 거리 구하기
 */
