import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dp, V;
    static int N, S, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[N + 1][M + 1];
        V = new int[N];

        dp[0][S] = true;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j]) {
                    if (j + V[i - 1] <= M) dp[i][j + V[i - 1]] = true;
                    if (j - V[i - 1] >= 0) dp[i][j - V[i - 1]] = true;
                }
            }
        }

        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}

/*
조건
- V[i] : i 번째 곡을 연주하기 전에 바꿀 수 있는 볼륨
- P - V[i] <= i번 곡 <= P + V[i]
- 0보다 작거나 보다 크게는 못함

요구
- 마지막 곡을 연주할 수 있는 볼륨 중 최댓값

풀이
- dp
- 올 수 있는 상태
dp[i - 1] + V[i] vs dp[i - 1] - V[i];
 */