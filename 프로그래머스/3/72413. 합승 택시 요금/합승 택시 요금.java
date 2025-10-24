import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;        
        
        int[][] dist = new int[n + 1][n + 1];
        
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int c = fare[2];
            
            dist[u][v] = c;
            dist[v][u] = c;
        }
    
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
}

/*
조건
- 택시를 동료와 타서 돈 아낌.
- 같이 가다가 중간에 갈라짐.
- S 출발점

요구
- 최저 계산
*/