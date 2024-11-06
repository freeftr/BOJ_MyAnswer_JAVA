import java.io.*;
import java.util.*;

public class Main {

    static final int MIN_VALUE = -32768 * 100;
    static int[][] dp;
    static int[] preSum;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(br.readLine());
            preSum[i] = preSum[i - 1] + a;
        }

        dp = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], MIN_VALUE);
        }
        System.out.println(dfs(N, M));
    }

    static int dfs(int idx, int section) {
        if (section == 0) {
            return 0;
        }
        if (idx <= 0) {
            return MIN_VALUE;
        }

        if (visited[idx][section]) {
            return dp[idx][section];
        }
        visited[idx][section] = true;

        dp[idx][section] = dfs(idx - 1, section);

        for (int i = idx; i >= 1; i--) {
            dp[idx][section] = Math.max(dp[idx][section], dfs(i - 2, section - 1) + (preSum[idx] - preSum[i - 1]));
        }
        return dp[idx][section];
    }
}