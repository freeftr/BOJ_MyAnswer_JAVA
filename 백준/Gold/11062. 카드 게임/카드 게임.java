import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, T;
    static int[] cards;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cards = new int[N];
            dp = new int[2][N][N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < N; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            sb.append(dfs(0, 0, N - 1)).append("\n");
        }
        System.out.print(sb);
    }

    static int dfs(int turn, int left, int right) {
        if (dp[turn][left][right] != -1) {
            return dp[turn][left][right];
        }

        if (left == right) {
            return turn == 0 ? cards[left] : 0;
        }

        if (turn == 0) {
            dp[turn][left][right] = Math.max(
                    dfs(1, left + 1, right) + cards[left],
                    dfs(1, left, right - 1) + cards[right]
            );
        } else {
            dp[turn][left][right] = Math.min(
                    dfs(0, left + 1, right),
                    dfs(0, left, right - 1)
            );
        }

        return dp[turn][left][right];
    }
}
