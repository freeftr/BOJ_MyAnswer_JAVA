import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        q.add(new int[]{0,0,0,1});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int wall = cur[2];
            int dist = cur[3];

            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny][wall]) continue;

                if (wall == 0) {
                    if (map[nx][ny] == '0') {
                        q.add(new int[]{nx, ny, 0, dist + 1});
                        visited[nx][ny][0] = true;
                    } else {
                        q.add(new int[]{nx, ny, 1, dist + 1});
                        visited[nx][ny][1] = true;
                    }
                } else {
                    if (map[nx][ny] == '1') continue;
                    q.add(new int[]{nx, ny, 1, dist + 1});
                    visited[nx][ny][1] = true;
                }
            }
        }
        return -1;
    }
}
