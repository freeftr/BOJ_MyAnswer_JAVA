import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dist;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);
    }

    public static int dfs(int i, int j) {
        if (dist[i][j] != 0) {
            return dist[i][j];
        }

        dist[i][j] = 1;

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x < 0 || x >= n || y < 0 || y >= n) continue;

            if (map[x][y] > map[i][j]) {
                dist[i][j] = Math.max(dist[i][j], dfs(x, y) + 1);
            }
        }
        return dist[i][j];
    }
}