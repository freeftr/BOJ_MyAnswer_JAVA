import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            long[] dp = new long[N + 1];

            if (N >= 1) dp[1] = 0;
            if (N >= 2) dp[2] = 1;

            for (int i = 3; i <= N; i++) {
                dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
            }

            System.out.println(dp[N]);
        }
    }
}
