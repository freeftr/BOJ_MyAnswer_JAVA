import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int alp_target = 0;
        int cop_target = 0;
        
        for (int[] p : problems) {
            int alp_req = p[0];
            int cop_req = p[1];
            
            alp_target = Math.max(alp_req, alp_target);
            cop_target = Math.max(cop_req, cop_target);
        }
        
        // 이미 충족한 경우
        if (alp >= alp_target && cop >= cop_target) return 0;
        
        alp = Math.min(alp, alp_target);
        cop = Math.min(cop, cop_target);
        
        int[][] dp = new int[alp_target + 2][cop_target + 2];
        
        for (int i = 0; i <= alp_target; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= alp_target; i++) {
            for (int j = cop; j <= cop_target; j++) {
                // 공부하는 경우
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                
                for (int[] p : problems) {
                    int alp_req = p[0];
                    int cop_req = p[1];
                    int alp_rwd = p[2];
                    int cop_rwd = p[3];
                    int cost = p[4];
                    
                    // 현재 문제 풀 수 없는 경우
                    if (i < alp_req || j < cop_req) continue;
                    
                    int na = Math.min(i + alp_rwd, alp_target);
                    int nc = Math.min(j + cop_rwd, cop_target);
                    
                    dp[na][nc] = Math.min(dp[i][j] + cost, dp[na][nc]);
                }
            }
        }
        
        return dp[alp_target][cop_target];
    }
}

/*
알고, 코딩 모두 기준치 넘어야 문제 푸는거 가능
알고공부 = 1시간에 1증가
코딩공부 = 1시간에 1증가
문제풀면 주어진만큼 증가
같은 문제 푸는거 가능
모든 문제 풀 수 있는 알, 코 얻는 최단 시간 구하기.

- dp[목표 알고력][목표 코딩력]
*/