import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] a, m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N];
        m = new int[N];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
            sum += m[i];
        }

        int[][] dp = new int[N][sum + 1];

        for (int j = 0; j <= sum; j++) {
            if (m[0] <= j) dp[0][j] = a[0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (m[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - m[i]] + a[i]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j <= sum; j++) {
            if (dp[N - 1][j] >= M) {
                answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }
}
