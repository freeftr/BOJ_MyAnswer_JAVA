import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int sx = 0, sy = 0;
    static int ex = 0, ey = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dijkstra());
    }

    public static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{sx, sy, 0, 1});

        int[][][] dist = new int[3][N][M];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        dist[0][sx][sy] = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0];
            int y = p[1];
            int cost = p[2];
            int move = p[3];

            if (x == ex && y == ey) {
                return cost;
            }

            int idx = move % 3;

            for (int i = 0; i < 4; i++) {
                if (idx == 1) {
                    if (i == 1 || i == 3) continue;
                } else if (idx == 2) {
                    if (i == 0 || i == 2) continue;
                }

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == -1) continue;

                if (dist[idx][nx][ny] > cost + map[nx][ny]) {
                    dist[idx][nx][ny] = cost + map[nx][ny];
                    pq.offer(new int[]{nx, ny, dist[idx][nx][ny], (move + 1) % 3});
                }
            }
        }
        return -1;
    }
}
