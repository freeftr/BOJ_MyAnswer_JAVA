import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {

        int N = info.length;
        int INF = 1_000_000_000;

        int[][] dp = new int[N][m];
        for (int[] row : dp) Arrays.fill(row, INF);

        dp[0][0] = info[0][0];
        if (info[0][1] < m) dp[0][info[0][1]] = 0;

        for (int i = 1; i < N; i++) {
            for (int b = 0; b < m; b++) {

                if (dp[i - 1][b] == INF) continue;

                // A가 훔침
                dp[i][b] = Math.min(dp[i][b], dp[i - 1][b] + info[i][0]);

                // B가 훔침
                int nb = b + info[i][1];
                if (nb < m) {
                    dp[i][nb] = Math.min(dp[i][nb], dp[i - 1][b]);
                }
            }
        }

        int ans = INF;

        for (int b = 0; b < m; b++) {
            ans = Math.min(ans, dp[N - 1][b]);
        }

        if (ans >= n) return -1;

        return ans;
    }
}