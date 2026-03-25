import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dist = new int[N + 1][N + 1];

            for (int i = 0; i < N + 1; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                dist[a][b] = c;
                dist[b][a] = c;
            }

            int K = Integer.parseInt(br.readLine());
            int[] friends = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friends[i] = Integer.parseInt(st.nextToken());
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                        dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int sum = 0;

                for (int j = 0; j < K; j++) {
                    if (dist[i][friends[j]] == Integer.MAX_VALUE) continue;
                    sum += dist[i][friends[j]];
                }

                if (sum < min) {
                    answer = i;
                    min = sum;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- N 개의 방, M개의 양방향 통로

요구
- 친구들로부터 거리가 최소인 곳 찾기

풀이
- 플로이드 워셜
 */