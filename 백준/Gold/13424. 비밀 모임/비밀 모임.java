import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] dist = new int[N + 1][N + 1];

            for (int i = 0; i <= N; i++) {
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
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int answer = -1;

            for (int i = 1; i <= N; i++) {
                int sum = 0;

                for (int friend : friends) {
                    sum += dist[i][friend];
                }

                if (sum < min) {
                    min = sum;
                    answer = i;
                }
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }
}

/*
조건
- N개의 방, M개의 통로
- 양방향
- 모두한테서 이동거리가 최소가 된느 방 고르기

요구
- 거리 총합 최소 모임 장소 출력
 */