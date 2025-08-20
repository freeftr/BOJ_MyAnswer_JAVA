import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int at = 0, ac = 0;
        for (int[] p : problems) {
            at = Math.max(at, p[0]);
            ac = Math.max(ac, p[1]);
        }

        if (alp >= at && cop >= ac) return 0;

        alp = Math.min(alp, at);
        cop = Math.min(cop, ac);

        final int INF = 1_000_000_000;
        int[][] dp = new int[at + 2][ac + 2];
        for (int i = 0; i < at + 2; i++) Arrays.fill(dp[i], INF);
        dp[alp][cop] = 0;

        for (int i = alp; i <= at; i++) {
            for (int j = cop; j <= ac; j++) {
                if (dp[i][j] == INF) continue;

                int ni = Math.min(at, i + 1);
                int nj = Math.min(ac, j + 1);
                dp[ni][j] = Math.min(dp[ni][j], dp[i][j] + 1);
                dp[i][nj] = Math.min(dp[i][nj], dp[i][j] + 1);

                for (int[] p : problems) {
                    int reqa = p[0], reqc = p[1];
                    int rewa = p[2], rewc = p[3];
                    int cost = p[4];
                    if (i < reqa || j < reqc) continue;

                    int ni2 = Math.min(at, i + rewa);
                    int nj2 = Math.min(ac, j + rewc);
                    dp[ni2][nj2] = Math.min(dp[ni2][nj2], dp[i][j] + cost);
                }
            }
        }
        return dp[at][ac];
    }
}
