import java.util.*;

class Solution {
    static int[][] dist;
    static final int INF = 20000001;  // n 최대 200개, 요금 최대 100000 이므로 최댓값보다 큰 값 설정
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];

        // 거리 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;  // 자기 자신으로 가는 비용은 0
        }

        // 주어진 요금 정보 입력
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // 최소 비용 계산
        int minCost = dist[s][a] + dist[s][b];  // 각자 타고 가는 경우

        // 모든 분기점 i를 고려
        for (int i = 1; i <= n; i++) {
            if (dist[s][i] == INF || dist[i][a] == INF || dist[i][b] == INF) {
                continue;
            }
            minCost = Math.min(minCost, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return minCost;
    }
}
