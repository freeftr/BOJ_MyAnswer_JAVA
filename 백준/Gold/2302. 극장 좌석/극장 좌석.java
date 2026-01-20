import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer> ranges = new ArrayList<>();
        int prev = 0;
        int limit = 0;
        int last = 0;

        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            if (i == M - 1) last = vip;
            ranges.add(vip - prev - 1);
            prev = vip;
            limit = Math.max(limit, ranges.get(i));
        }

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        long answer = 1;

        for (int r : ranges) {
            answer *= dp[r];
        }

        answer = answer * dp[(N - last)];

        System.out.println(answer);
    }
}

/*
조건
- N개의 좌석
- 자기 양옆으로 옮길 수 있음.
- VIP는 고정

요구
- 앉는 경우의 수 구하기

풀이
- VIP는 고정시켜 놓고 구간들끼리 경우의 수 구하면 된다.
- 구간들끼리 경우의 수는 dp로 구할 수 있음.

dp[1] = 1

dp[2] = 2
12
21

dp[3] = 3
123
213
132

dp[4] = 5
1234
1243
1324
3124
2143
 */