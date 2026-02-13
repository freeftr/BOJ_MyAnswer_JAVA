import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1234567;
    static int[][] keypad = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {0, -1, -1}   // 없는 칸은 -1
    };

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[][][] dp = new int[1001][4][3];

        // 길이 1
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (keypad[r][c] != -1)
                    dp[1][r][c] = 1;
            }
        }

        // DP
        for (int len = 2; len <= 1000; len++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {

                    if (keypad[r][c] == -1) continue;

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nc < 0 || nr >= 4 || nc >= 3) continue;
                        if (keypad[nr][nc] == -1) continue;

                        dp[len][r][c] =
                                (dp[len][r][c] + dp[len - 1][nr][nc]) % MOD;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int sum = 0;
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    if (keypad[r][c] != -1)
                        sum = (sum + dp[N][r][c]) % MOD;
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
