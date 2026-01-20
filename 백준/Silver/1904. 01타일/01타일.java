import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1 || N == 2) {
            System.out.println(N);
            return;
        }
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[N]);
    }
}

/*
조건
- 00 또는 1 타일

요구
- N자리 숫 이진 수열 만들 수 있는 가짓수 구하기

풀이
- dp

dp[1] = 1
1

dp[2] = 2
11
00

dp[3] = 3
100
001
111

dp[4] = 5
 */