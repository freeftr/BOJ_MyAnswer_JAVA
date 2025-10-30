import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int sx, sy, ex, ey;
    static char[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int time = 1;
        while (true) {
            boolean result = bfs();

            if (result) break;

            time++;
        }

        System.out.println(time);
    }

    static boolean bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] == '0') {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }

                if (map[nx][ny] == '1') {
                    visited[nx][ny] = true;
                    map[nx][ny] = '0';
                }

                if (map[nx][ny] == '#') return true;
            }
        }

        return false;
    }
}

/*
조건
-
 */