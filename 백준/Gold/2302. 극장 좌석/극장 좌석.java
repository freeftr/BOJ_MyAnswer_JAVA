import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer> vips = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            vips.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(vips);

        int[] dp = new int[N + 1];
        dp[0] = 1;
        if (N >= 1) dp[1] = 1;
        if (N >= 2) dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;
        int prev = 1;

        for (int vip : vips) {
            int len = vip - prev;
            answer *= dp[len];
            prev = vip + 1;
        }

        answer *= dp[N - prev + 1];

        System.out.println(answer);
    }
}
