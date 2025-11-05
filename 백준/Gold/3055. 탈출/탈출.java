import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[][] water;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new int[R][C];
        int sx = 0, sy = 0;

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            Arrays.fill(water[i], INF);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        rain();
        int result = findPath(sx, sy);

        if (result == -1) System.out.println("KAKTUS");
        else System.out.println(result);
    }

    static void rain() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'D') {
                    water[i][j] = INF;
                }
                if (map[i][j] == '*') {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                    water[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visited[nx][ny] || map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;

                visited[nx][ny] = true;
                water[nx][ny] = time + 1;
                q.add(new int[]{nx, ny, time + 1});
            }
        }
    }

    static int findPath(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        q.add(new int[]{a, b, 0});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            if (map[x][y] == 'D') return time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'X') continue;
                if (water[nx][ny] <= time + 1) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, time + 1});
            }
        }

        return -1;
    }
}


/*
조건
- 비버의 굴로 가자.
- 물은 매분마다 확장 (*)
- 다음시간에 물이 찰 곳으로 이동못함.

요구
- 비버굴로 가는 가장 빠른 시간.
 */