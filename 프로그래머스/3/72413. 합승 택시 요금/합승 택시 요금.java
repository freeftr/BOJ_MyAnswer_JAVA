import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int[][] dist = new int[n + 1][n + 1];
        
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        int no = dist[s][a] + dist[s][b];
        int yes = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            int A = dist[i][a];
            int B = dist[i][b];
            if (i == a) A = 0;
            if (i == b) B = 0;
            yes = Math.min(yes, dist[s][i] + A + B);
        }
        
        return Math.min(yes, no);
    }
}

/*
노드 = 지점
간선 = 택시노선, 예상요금

플로이드 워셜로 각 지점별 최단거리 구하기.

*/