import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[M][N];

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[M][N];

        for (int i = 0; i < N; i++) {
            if (map[0][i] == '0' && !visited[0][i]) {
                boolean canReach = bfs(0, i);

                if (canReach) {
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");
    }

    static boolean bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == M - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == '1') continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return false;
    }
}

/*
조건
- 격자의 위 바깥쪽 격자의 아래 안쪽
- 검은색: 전류 차단.
- 흰색: 전류 통함.

요구
- 바깥쪽에서 흘려준 전류가 안쪽에 올 수 있는지 아닌지.

풀이
- 위에서부터 0이면 bfs 실시 해서 아래로 도착하는지 확인.
 */
