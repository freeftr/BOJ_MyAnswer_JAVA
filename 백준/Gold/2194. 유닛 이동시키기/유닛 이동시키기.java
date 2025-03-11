import java.io.*;
import java.util.*;

public class Main {
    static int N, M, A, B, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs(sx, sy, ex, ey));
    }

    public static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], cnt = cur[2];

            if (x == ex && y == ey) return cnt;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (check(nx, ny)) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, cnt + 1});
                }
            }
        }
        return -1;
    }

    public static boolean check(int x, int y) {
        if (x < 0 || y < 0 || x + A > N || y + B > M || visited[x][y]) return false;

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                if (map[x + i][y + j] == 1) return false;
            }
        }
        return true;
    }
}
