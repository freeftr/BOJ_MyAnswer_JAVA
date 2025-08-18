import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        final int MOD = 1_000_000_007;

        int[][] dp = new int[n][m];
        boolean[][] blocked = new boolean[n][m];

        for (int[] p : puddles) {
            int x = p[0] - 1;
            int y = p[1] - 1;
            if (0 <= y && y < n && 0 <= x && x < m) {
                blocked[y][x] = true;
            }
        }

        dp[0][0] = 1;

        for (int j = 1; j < m; j++) {
            if (blocked[0][j]) break;
            dp[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (blocked[i][0]) break;
            dp[i][0] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (blocked[i][j]) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
