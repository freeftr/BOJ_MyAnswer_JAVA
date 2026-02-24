import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (b == 0) {
                dist[u][v] = 0;
                dist[v][u] = 1;
            } else {
                dist[u][v] = 0;
                dist[v][u] = 0;
            }
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

//        for (int i = 1; i < n + 1; i++) {
//            System.out.println();
//            for (int j = 1; j < n + 1; j++) {
//                System.out.printf("%2d", dist[i][j] == Integer.MAX_VALUE ? -1 : dist[i][j]);
//            }
//        }
//        System.out.println();

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            System.out.println(dist[s][e]);
        }
    }
}

/*
조건
- 목적지까지 가는 길이 없음
- 일방통행인 길을 양방향으로 바꿔야 하는 길을 구해야 함.

요구
- 목적지까지 가려면 최소 몇개의 길을 양방향으로 바꿔야 하는지

풀이
-
 */