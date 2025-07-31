import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean flag = false;

    public static void main(String[] args) throws IOException{
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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                flag = false;
                visited = new boolean[N][M];
                visited[i][j] = true;
                dfs(1, i, j, i, j);
                if (flag) {
                    System.out.print("Yes");
                    return;
                }
            }
        }

        System.out.print("No");
    }

    static void dfs(int depth, int x, int y, int a, int b) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx == a && ny == b && depth >= 4) {
                flag = true;
            }

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny]) continue;
            if (map[x][y] != map[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(depth + 1, nx, ny, a, b);
            visited[nx][ny] = false;
        }
    }
}
