import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] walk, bicycle;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        walk = new int[N][2];
        bicycle = new int[N][2];
        dp = new int[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());
            bicycle[i][0] = Integer.parseInt(st.nextToken());
            bicycle[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int[] row : dp) Arrays.fill(row, -1);
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }

                if (j + walk[i][0] <= K) {
                    dp[i + 1][j + walk[i][0]] = Math.max(dp[i + 1][j + walk[i][0]], dp[i][j] + walk[i][1]);
                }

                if (j + bicycle[i][0] <= K) {
                    dp[i + 1][j + bicycle[i][0]] = Math.max(dp[i + 1][j + bicycle[i][0]], dp[i][j] + bicycle[i][1]);
                }
            }
        }

        int maxMoney = 0;
        for (int j = 0; j <= K; j++) {
            maxMoney = Math.max(maxMoney, dp[N][j]);
        }

        System.out.println(maxMoney);
    }
}
