import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) break;
                if (i + board[i][j] < N) dp[i + board[i][j]][j] += dp[i][j];
                if (j + board[i][j] < N) dp[i][j + board[i][j]] += dp[i][j];
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println();
//            for (int j = 0; j < N; j++) {
//                System.out.printf("%3d", dp[i][j]);
//            }
//        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
