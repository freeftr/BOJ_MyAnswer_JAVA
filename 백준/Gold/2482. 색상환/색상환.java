import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static final int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                if (j > i / 2) {
                    break;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        System.out.println(dp[n][k]);
    }
}
