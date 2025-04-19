import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = 100000;
        int[][] dp = new int[n][m]; 
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int[] item : info) {
            int aTrace = item[0];
            int bTrace = item[1];
            int[][] next = new int[n][m];
            for (int[] row : next) Arrays.fill(row, INF);

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (dp[a][b] == INF) continue;

                    int nextA = a + aTrace;
                    int nextB = b;
                    if (nextA < n) {
                        next[nextA][nextB] = Math.min(next[nextA][nextB], dp[a][b] + aTrace);
                    }

                    nextA = a;
                    nextB = b + bTrace;
                    if (nextB < m) {
                        next[nextA][nextB] = Math.min(next[nextA][nextB], dp[a][b]);
                    }
                }
            }

            dp = next;
        }

        int minTrace = INF;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b] != INF) {
                    minTrace = Math.min(minTrace, dp[a][b]);
                }
            }
        }

        return minTrace == INF ? -1 : minTrace;
    }
}
