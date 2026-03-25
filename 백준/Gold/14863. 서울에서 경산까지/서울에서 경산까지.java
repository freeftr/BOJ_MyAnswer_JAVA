import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] walk = new int[N + 1][2];
        int[][] bike = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());
            bike[i][0] = Integer.parseInt(st.nextToken());
            bike[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j - walk[i][0] >= 0 && dp[i - 1][j - walk[i][0]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - walk[i][0]] + walk[i][1]);
                }
                if (j - bike[i][0] >= 0 && dp[i - 1][j - bike[i][0]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - bike[i][0]] + bike[i][1]);
                }
            }
        }

        int answer = 0;
        for (int j = 0; j <= K; j++) {
            if (dp[N][j] != -1) {
                answer = Math.max(answer, dp[N][j]);
            }
        }

        System.out.println(answer);
    }
}