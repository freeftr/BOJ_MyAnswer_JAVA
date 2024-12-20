import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] A;
    static int[] dp = new int[N];
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, 1001);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= i+A[i]; j++) {
                if(j>=N) continue;

                dp[j] = Math.min(dp[i]+1, dp[j]);
            }
        }
        System.out.println(dp[N-1] == 1001 ? -1 : dp[N-1]);
    }
}