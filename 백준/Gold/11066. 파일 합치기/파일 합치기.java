import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] preSum = new int[N + 1];
            int[][] dp = new int[N][N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                preSum[i + 1] = preSum[i] + a;
            }

            for (int len = 1; len < N; len++) {
                for (int start = 0; start + len < N; start++) {
                    int end = start + len;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + preSum[end + 1] - preSum[start]);
                    }
                }
            }

            sb.append(dp[0][N - 1]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
