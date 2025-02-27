import java.util.*;

class Solution {
    static int[][] dist;
    static final int INF = 20000001;  
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;  
        }

        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int minCost = dist[s][a] + dist[s][b]; 

        for (int i = 1; i <= n; i++) {
            if (dist[s][i] == INF || dist[i][a] == INF || dist[i][b] == INF) {
                continue;
            }
            minCost = Math.min(minCost, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return minCost;
    }
}
