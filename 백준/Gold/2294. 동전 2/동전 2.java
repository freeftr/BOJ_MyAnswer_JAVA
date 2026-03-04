import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        int[] dp = new int[110001];
        Arrays.fill(dp, 10001);

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
            dp[coin[i]] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + coin[j]] = Math.min(dp[i + coin[j]], dp[i] + 1);
            }

//            System.out.println();
//            for (int j = 1; j <= k; j++) {
//                System.out.printf("%3d", dp[j] >= 10000 ? -1 : dp[j]);
//            }
        }

        System.out.println(dp[k] >= 10001 ? -1 : dp[k]);
    }
}

/*
조건
- n가지 동전
- 가치의 합의 k원
- 동전의 개수가 최소가 되도록

요구
- k원을 맞추는데 동전의 개수를 최소로 맞추기

풀이
- dp

1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
1               1                           1

 */