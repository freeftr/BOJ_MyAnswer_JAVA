import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] price = new int[k];
        int[] quantity = new int[k];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            quantity[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[T + 1];

        dp[0] = 1;

        for (int i = 0; i < k; i++) {
            int p = price[i];
            int q = quantity[i];

            for (int j = T; j >= 0; j--) {
                for (int cnt = 1; cnt <= q; cnt++) {
                    if (j - p * cnt < 0) break;
                    dp[j] += dp[j - cnt * p];
                }
            }
        }

        System.out.println(dp[T]);
    }
}

/*
조건
- k가지 동전
- T원의 지폐를 동전으로 바꿔줌

요구
- p원을 동전으로 바꾸는 경우의 수 구하기

풀이
- dp
- 계속 더해줘야 함.
 */