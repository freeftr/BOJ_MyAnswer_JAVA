import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 3 || N == 5) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[5010];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[3] = 1;
        dp[5] = 1;

        for (int i = 0; i <= N; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            dp[i + 3] = Math.min(dp[i] + 1, dp[i + 3]);
            dp[i + 5] = Math.min(dp[i] + 1, dp[i + 5]);
        }

        if (dp[N] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[N]);
    }
}

/*
조건
- 3키로, 5키로
- 더 적은 개수로

요구
- 몇개만 가져가면 되는지.
 */