import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            arr[i][0] = W;
            arr[i][1] = V;
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            int w = arr[i][0];
            int v = arr[i][1];
            for (int j = 0; j <= K; j++) {
                if (j - w < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - w] + v, dp[i - 1][j]);
            }
        }

        System.out.println(dp[N][K]);
    }
}


/*
조건
- N개의 물건
- 무게 W, 가치 V

요구
- 무게 K를 넘지 않으면서 최대 가치

풀이
- 배낭
무게 가치
6 13
4 8
3 6
5 12
세로축 물건의 개수, 가로축 무게 -> 최대 가치

0   1   2   3   4   5   6   7
0   0   0   0   0   0   0   0
1   0   0   0   0   0   13  13
2   0   0   0   8   8   13  13
3   0   0   6   8   8   13  14
4   0   0   6   8   12  13  14
 */