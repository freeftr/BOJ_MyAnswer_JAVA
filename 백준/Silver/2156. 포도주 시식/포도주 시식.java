import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] cups = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cups[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(cups[1]);
            return;
        }

        int[] dp = new int[n + 1];

        dp[1] = cups[1];
        dp[2] = cups[1] + cups[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1],
                    Math.max(
                            cups[i] + dp[i - 2],
                            cups[i] + cups[i - 1] + dp[i - 3]
                    )
            );
        }

        System.out.println(dp[n]);
    }
}