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

        int idx = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            int[][] graph = new int[N][3];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                graph[i][0] = Integer.parseInt(st.nextToken());
                graph[i][1] = Integer.parseInt(st.nextToken());
                graph[i][2] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N][3];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], 1000000000);
            }

            dp[0][1] = graph[0][1];
            dp[0][2] = graph[0][1] + graph[0][2];

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][0] + graph[i][0], dp[i - 1][1] + graph[i][0]);

                dp[i][1] = Math.min(dp[i][0] + graph[i][1], dp[i - 1][1] + graph[i][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + graph[i][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][2] + graph[i][1]);

                dp[i][2] = Math.min(dp[i][1] + graph[i][2], dp[i - 1][1] + graph[i][2]);
                dp[i][2] = Math.min(dp[i][2], dp[i - 1][2] + graph[i][2]);
            }

            sb.append(idx + ". " + dp[N - 1][1]).append("\n");
            idx++;
        }

        System.out.println(sb);
    }
}

/*
조건
- 가장 위쪽 가운데 -> 아래 가운데
- 비용 = 지나간 정점의 합

요구
- 최소 비용
 */