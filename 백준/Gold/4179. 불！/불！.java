import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static Queue<int[]> fire = new ArrayDeque<>();
    static Queue<int[]> jihoon = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'J') {
                    map[i][j] = '.';
                    jihoon.add(new int[]{i, j, 0});  // 지훈이의 초기 위치와 시간을 함께 저장
                }

                if (map[i][j] == 'F') {
                    fire.add(new int[]{i, j});
                }
            }
        }
        bfs();
    }

    public static void bfs() {
        boolean[][] visited = new boolean[R][C];

        while (!jihoon.isEmpty()) {
            // 먼저 불을 퍼뜨림
            setfire();

            // 지훈이의 이동 처리
            int size = jihoon.size();
            for (int i = 0; i < size; i++) {
                int[] arr = jihoon.poll();
                int x = arr[0];
                int y = arr[1];
                int time = arr[2];

                // 만약 지훈이가 미로의 가장자리에 도착하면 탈출 성공
                if (x == 0 || y == 0 || x == R - 1 || y == C - 1) {
                    System.out.println(time + 1);
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    // 범위를 벗어나지 않도록 체크
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    // 방문하지 않은 빈 공간만 이동 가능
                    if (visited[nx][ny] || map[nx][ny] != '.') continue;

                    visited[nx][ny] = true;
                    jihoon.add(new int[]{nx, ny, time + 1});
                }
            }
        }
        System.out.println("IMPOSSIBLE");  // 지훈이가 탈출하지 못한 경우
    }

    public static void setfire() {
        int size = fire.size();

        for (int i = 0; i < size; i++) {
            int[] arr = fire.poll();
            int x = arr[0];
            int y = arr[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == '.') {
                    map[nx][ny] = 'F';
                    fire.add(new int[]{nx, ny});
                }
            }
        }
    }
}