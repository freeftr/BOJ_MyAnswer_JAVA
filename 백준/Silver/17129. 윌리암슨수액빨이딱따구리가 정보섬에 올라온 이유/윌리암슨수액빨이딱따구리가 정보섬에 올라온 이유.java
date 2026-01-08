import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s[j]);

                if (map[i][j] == 2) {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (map[x][y] > 2) {
                System.out.println("TAK");
                System.out.println(dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;

                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }

        System.out.println("NIE");
    }
}

/*
조건
- 아빠 -> 청국장
- 엄마 -> 스시
- 아이 -> 맥앤치즈

요구
- 어떤 음식에 가장 빨리 도착하는지
 */
