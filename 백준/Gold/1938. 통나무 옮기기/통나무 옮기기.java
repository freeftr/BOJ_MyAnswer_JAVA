import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;

    static int cx, cy;
    // 세로인 상태
    static int vertical = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'B') {
                    if (j > 0 && map[i][j - 1] == 'B') {
                        vertical = 0; // 수평 상태
                    }
                    cnt++;
                    if (cnt == 2) {
                        cx = i;
                        cy = j;
                    }
                }


            }
        }
//        System.out.println(cx + " " + cy);
        int ans = bfs();
        System.out.println(ans);
    }

    static int[] dx = {1, 1, 1, -1, -1, -1, 0, 0}; // 방향 벡터
    static int[] dy = {0, 1, -1, -1, 0, 1, 1, -1};

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][2];
        q.offer(new int[]{cx, cy, 0, vertical});
        visited[cx][cy][vertical] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            int vert = cur[3];

//            System.out.println(x+" "+y+" "+dist+" "+vert);

            // 목적지 도착 확인
            if (map[x][y] == 'E') {
                if (vert == 0 && y > 0 && y < N - 1 && map[x][y - 1] == 'E' && map[x][y + 1] == 'E') {
                    return dist;
                } else if (vert == 1 && x > 0 && x < N - 1 && map[x - 1][y] == 'E' && map[x + 1][y] == 'E') {
                    return dist;
                }
            }

            // 이동
            if (vert == 0) { // 수평
                // 좌
                if (y > 1 && map[x][y - 1] != '1' && map[x][y - 2] != '1' && !visited[x][y - 1][0]) {
                    q.offer(new int[]{x, y - 1, dist + 1, 0});
                    visited[x][y - 1][0] = true;
                }
                // 우
                if (y < N - 2 && map[x][y + 1] != '1' && map[x][y + 2] != '1' && !visited[x][y + 1][0]) {
                    q.offer(new int[]{x, y + 1, dist + 1, 0});
                    visited[x][y + 1][0] = true;
                }
                // 아래
                if (x < N - 1 && map[x + 1][y] != '1' && map[x + 1][y - 1] != '1' && map[x + 1][y + 1] != '1' && !visited[x + 1][y][0]) {
                    q.offer(new int[]{x + 1, y, dist + 1, 0});
                    visited[x + 1][y][0] = true;
                }
                // 위
                if (x > 0 && map[x - 1][y] != '1' && map[x - 1][y - 1] != '1' && map[x - 1][y + 1] != '1' && !visited[x - 1][y][0]) {
                    q.offer(new int[]{x - 1, y, dist + 1, 0});
                    visited[x - 1][y][0] = true;
                }
        } else { // 수직
                // 위
                if (x > 1 && map[x - 1][y] != '1' && map[x - 2][y] != '1' && !visited[x - 1][y][1]) {
                    q.offer(new int[]{x - 1, y, dist + 1, 1});
                    visited[x - 1][y][1] = true;
                }
                // 아래
                if (x < N - 2 && map[x + 1][y] != '1' && map[x + 2][y] != '1' && !visited[x + 1][y][1]) {
                    q.offer(new int[]{x + 1, y, dist + 1, 1});
                    visited[x + 1][y][1] = true;
                }
                // 좌
                if (y > 0 && map[x][y - 1] != '1' && map[x - 1][y - 1] != '1' && map[x + 1][y - 1] != '1' && !visited[x][y - 1][1]) {
                    q.offer(new int[]{x, y - 1, dist + 1, 1});
                    visited[x][y - 1][1] = true;
                }
                // 우
                if (y < N - 1 && map[x][y + 1] != '1' && map[x - 1][y + 1] != '1' && map[x + 1][y + 1] != '1' && !visited[x][y + 1][1]) {
                    q.offer(new int[]{x, y + 1, dist + 1, 1});
                    visited[x][y + 1][1] = true;
                }

            }

            // 회전 여부 검사
            boolean canRotate = true;
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '1') {
                    canRotate = false;
                    break;
                }
            }

            if (canRotate) {
                int newVert = (vert == 1) ? 0 : 1; // 수평 <-> 수직
                if (!visited[x][y][newVert]) {
                    q.offer(new int[]{x, y, dist + 1, newVert});
                    visited[x][y][newVert] = true;
                }
            }
        }
        return 0;
    }
}