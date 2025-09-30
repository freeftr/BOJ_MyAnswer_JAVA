import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] dist = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        
        for (int[] result : results) {
            dist[result[0]][result[1]] = 1;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {                
                if (dist[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dist[i][j] != Integer.MAX_VALUE || dist[j][i] != Integer.MAX_VALUE) {
                    cnt++;
                }
            }
            
            if (cnt == n - 1) answer++;
        }
        return answer;
    }
}