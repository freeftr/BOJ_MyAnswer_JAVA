import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];

        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1];

            if (i % 2 == 0) {
                dp[i] = (dp[i] + dp[i / 2]) % MOD;
            }
        }

        System.out.println(dp[N] % MOD);
    }
}