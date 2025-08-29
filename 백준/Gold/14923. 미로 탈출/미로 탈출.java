import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int Hx, Hy;
    static int Ex, Ey;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Hx = Integer.parseInt(st.nextToken()) - 1;
        Hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        Ex = Integer.parseInt(st.nextToken()) - 1;
        Ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][M][2];

        q.add(new int[]{Hx, Hy, 0, 0});
        visited[Hx][Hy][0] = true;

        int result = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int w = cur[2];
            int cnt = cur[3];

            if (x == Ex && y == Ey) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny][w]) continue;

                if (w == 1) {
                    if (map[nx][ny] == 1) continue;
                    q.add(new int[]{nx, ny, 1, cnt + 1});
                    visited[nx][ny][1] = true;
                } else {
                    if (map[nx][ny] == 1) {
                        q.add(new int[]{nx, ny, 1, cnt + 1});
                        visited[nx][ny][1] = true;
                    } else {
                        q.add(new int[]{nx, ny, 0, cnt + 1});
                        visited[nx][ny][0] = true;
                    }
                }
            }
        }
        return result;
    }
}
