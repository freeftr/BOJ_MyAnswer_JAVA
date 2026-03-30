import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] time = new int[N + 1];
        int[] score = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            time[i] = K;
            score[i] = S;
        }

        int[][] dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= T; j++) {
                if (j >= time[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - time[i]] + score[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        System.out.println(dp[N][T]);
    }
}

/*
조건
- 한 단원당 한 문제

요구
- 남은 시간 동안 공부해서 얻을 수 있는 최대 점수

풀이
- 배낭

    0   1   2   3   4   5   6   7   8   9
0   0   0   0   0   0   0   0   0   0   0
1
2
3

 */