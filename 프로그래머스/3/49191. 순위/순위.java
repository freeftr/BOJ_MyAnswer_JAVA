import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int[][] results) {
        final int INF = 1_000_000_000;
        int[][] dist = new int[n + 1][n + 1];

        // 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] r : results) {
            int a = r[0], b = r[1];
            dist[a][b] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int known = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dist[i][j] != INF || dist[j][i] != INF) known++;
            }
            if (known == n - 1) answer++;
        }
        return answer;
    }
}
