import java.util.*;
import java.io.*;

public class Main {
    // 이동 비용을 나타내는 2차원 배열
    static int[][] move = {
            {0, 2, 2, 2, 2},
            {2, 1, 3, 4, 3},
            {2, 3, 1, 3, 4},
            {2, 4, 3, 1, 3},
            {2, 3, 4, 3, 1}
    };

    static ArrayList<Integer> command = new ArrayList<>();
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt();
            if (a == 0) break;
            command.add(a);
        }

        int n = command.size();
        dp = new int[n + 1][5][5];

        for (int i = 0; i <= n; i++) {
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    dp[i][a][b] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int depth = 1; depth <= n; depth++) {
            int next = command.get(depth - 1);

            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    if (dp[depth - 1][left][right] != Integer.MAX_VALUE) {
                        dp[depth][next][right] = Math.min(dp[depth][next][right], dp[depth - 1][left][right] + move[left][next]);
                        dp[depth][left][next] = Math.min(dp[depth][left][next], dp[depth - 1][left][right] + move[right][next]);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[n][i][j]);
            }
        }

        System.out.println(ans);
    }
}