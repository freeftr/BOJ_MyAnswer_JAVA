import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] before;
    static int[][] after;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N][M];
        after = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (before[i][j] != after[i][j] && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        boolean same = true;
        for (int i = 0; i < N && same; i++) {
            for (int j = 0; j < M; j++) {
                if (before[i][j] != after[i][j]) {
                    same = false;
                    break;
                }
            }
        }

        if (cnt == 0) {
            System.out.println("YES");
        } else if (cnt == 1 && same) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        int start = before[a][b];
        int target = after[a][b];
        q.add(new int[]{a, b});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            before[x][y] = target;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (before[nx][ny] != start) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
