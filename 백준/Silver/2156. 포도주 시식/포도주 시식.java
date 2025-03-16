import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1]; // i 번째 포도주를 마실 때 최대 값

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(arr[1]);
            return;
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            // 현재 거 마시고 그전 거 마시고 i-2는 건너 뛰기 vs 현재거 전에 거만 안마시기
            dp[i] = Math.max(dp[i - 3] + arr[i] + arr[i - 1], dp[i - 2] + arr[i]);
            // 현재 거 안마시기 마시기
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[N]);
    }
}
