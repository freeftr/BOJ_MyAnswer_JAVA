import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int[][] dp = new int[n][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = num[0];
        dp[0][1] = num[0];
        int answer = num[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + num[i], num[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + num[i]);
            answer = Math.max(answer, dp[i][0]);
            answer = Math.max(answer, dp[i][1]);
        }

        System.out.println(answer);
    }
}

/*
조건
- n개의 정수 수열에서 연속된 수의 최대 합을 구하기
- 수 하나를 제거해도 되고 안해도된다.

풀이
- dp[i][j] = i 까지 최대합 j = 몇번 지웠는지
10  -4  3   1   5   6   -35 12  21  -1
10  10  13  14  19  25  25
 */