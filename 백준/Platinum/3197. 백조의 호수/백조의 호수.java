import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] lake;
    static boolean check;
    static Queue<int[]> wq = new LinkedList<>();
    static Queue<int[]> sq = new LinkedList<>();
    static int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        lake = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                lake[i][j] = s.charAt(j);
                if (lake[i][j] == 'L') {
                    if (x1 == -1 && y1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                    lake[i][j]='.';
                }
                if (lake[i][j] == '.') {
                    wq.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[R][C];
        int day = 0;
        sq.add(new int[]{x1, y1});
        visited[x1][y1] = true;

        while (true) {
            bfs();
            if (check) break;
            melt();
            day++;
        }
        System.out.println(day);
    }

    public static void bfs() {
        Queue<int[]> nq = new LinkedList<>();
        while (!sq.isEmpty()) {
            int[] arr = sq.poll();
            int X = arr[0];
            int Y = arr[1];

            // 백조가 서로 만났는지 확인
            if (X == x2 && Y == y2) {
                check = true;
                return;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];

                // 유효한 위치인지 확인
                if (nx >= R || ny >= C || nx < 0 || ny < 0) continue;

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if (lake[nx][ny] == '.') {
                        sq.add(new int[]{nx, ny});
                    } else if (lake[nx][ny] == 'X') {
                        nq.add(new int[]{nx, ny});
                    }
                }
            }
        }

        sq = nq;
    }

    public static void melt() {
        int len = wq.size();
        for (int j = 0; j < len; j++) {
            int[] arr = wq.poll();
            int X = arr[0];
            int Y = arr[1];
            for (int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if (nx >= R || ny >= C || nx < 0 || ny < 0) continue;
                if (lake[nx][ny] == 'X') {
                    lake[nx][ny] = '.';
                    wq.add(new int[]{nx, ny});
                }
            }
        }
    }
}