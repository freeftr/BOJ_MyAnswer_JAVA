import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] num = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[T + 1][W + 1][3];

        for (int i = 1; i <= T; i++) {
            dp[i][0][1] = dp[i - 1][0][1] + (num[i] == 1 ? 1 : 0);
        }

        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= W; j++) {
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + (num[i] == 1 ? 1 : 0);
                dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]) + (num[i] == 2 ? 1 : 0);
            }
        }

        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, Math.max(dp[T][i][1], dp[T][i][2]));
        }

        System.out.println(answer);
    }
}

/*
조건
- 자두가 떨어져야 자두를 받아서 먹을 수 있음.
- 자두가 허공에 있어야 잡을 수 있음.
- 매초 두개의 나무중 하나에서 떨어짐.
- 그 아래 있어야 먹을 수 있음.
- T초 동안 낙하.
- W번 움직이기 가능.

요구
- 자두가 받을 수 있는 자두 개수 구하기.

풀이
- dp
- 옮기냐 안옮기냐
 */