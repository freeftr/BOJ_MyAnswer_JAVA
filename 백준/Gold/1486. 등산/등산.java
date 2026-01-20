import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, D;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int height(char c) {
        if ('A' <= c && c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = height(s.charAt(j));
            }
        }

        int[][] go = dijkstra(false);
        int[][] back = dijkstra(true);

        int answer = map[0][0];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (go[i][j] == Integer.MAX_VALUE || back[i][j] == Integer.MAX_VALUE) continue;
                int time = go[i][j] + back[i][j];
                if (time <= D) answer = Math.max(answer, map[i][j]);
            }
        }

        System.out.println(answer);
    }

    static int[][] dijkstra(boolean reverse) {
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], d = cur[2];
            if (d > dist[x][y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int from = reverse ? map[nx][ny] : map[x][y];
                int to   = reverse ? map[x][y] : map[nx][ny];

                int diff = Math.abs(to - from);
                if (diff > T) continue;

                int cost = (to <= from) ? 1 : diff * diff;

                if (dist[nx][ny] > d + cost) {
                    dist[nx][ny] = d + cost;
                    pq.add(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }
        return dist;
    }
}

/*
조건
- A ~ Z == 0 ~ 25
- a ~ z == 26 ~ 51
- 인접한 좌표 중 높이 차가 T보다 크지 않은곳으로 이동 가능.
- 높이가 낮거나 같은 곳으로 가면 1초
- 높은 곳으로 가면 높이 차 * 제곱 만큼
 */