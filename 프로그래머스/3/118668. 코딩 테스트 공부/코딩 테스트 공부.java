import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int target_alp = 0, target_cop = 0;
        for (int[] p : problems) {
            target_alp = Math.max(target_alp, p[0]);
            target_cop = Math.max(target_cop, p[1]);
        }

        alp = Math.min(alp, target_alp);
        cop = Math.min(cop, target_cop);

        int INF = Integer.MAX_VALUE / 4;
        int[][] dp = new int[target_alp + 1][target_cop + 1];
        for (int i = 0; i <= target_alp; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= target_alp; i++) {
            for (int j = cop; j <= target_cop; j++) {
                if (dp[i][j] == INF) continue;

                // 공부 전이
                if (i + 1 <= target_alp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= target_cop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 문제 풀이 전이
                for (int[] problem : problems) {
                    int req_alp = problem[0];
                    int req_cop = problem[1];
                    int rew_alp = problem[2];
                    int rew_cop = problem[3];
                    int cost = problem[4];

                    if (i >= req_alp && j >= req_cop) {
                        int new_alp = Math.min(target_alp, i + rew_alp);
                        int new_cop = Math.min(target_cop, j + rew_cop);
                        dp[new_alp][new_cop] = Math.min(dp[new_alp][new_cop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[target_alp][target_cop];
    }
}
