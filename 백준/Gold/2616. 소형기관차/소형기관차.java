import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] pre = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) pre[i] = pre[i - 1] + arr[i];

        int[][] dp = new int[4][N + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * M; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + (pre[j] - pre[j - M]));
            }
        }

        System.out.println(dp[3][N]);
    }
}
