import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[] dp = new int[10001];

        dp[0] = 1;

        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= 10000; j++) {
                dp[j] += dp[j - i];
            }
        }

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 정수를 1,2,3의 합으로 나타내기
- 순열이 아닌 조합

요구
- 주어진 정수를 1,2,3의 합으로 나타내는 방법의 수 구하기

풀이
- dp

dp[1] = 1;
dp[2] = 2;
dp[3] = 3;
dp[4] = 4;
dp[5] = 5;
dp[6] = 7;
dp[7] = 8;

dp[10] = 14;
 */
