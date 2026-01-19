import java.io.*;

public class Main {

    static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];
        int max = 0;

        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long[] dp = new long[max + 1];
        dp[1] = 1;
        if (max >= 2) dp[2] = 2;
        if (max >= 3) dp[3] = 4;

        for (int i = 4; i <= max; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
        }

        for (int n : arr) {
            sb.append(dp[n]).append("\n");
        }

        System.out.print(sb);
    }
}
