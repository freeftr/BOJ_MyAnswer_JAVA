import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] companies;
    static int[][] dp = new int[25][500];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        companies = new int[M+1][N + 1];
        int[][] path = new int[25][500];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                companies[j][a] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = dp[i-1][j];
                for (int k = 1; k <= j; k++) {
                    if(dp[i][j] < dp[i-1][j-k] + companies[i][k]){
                        dp[i][j] = dp[i-1][j-k] + companies[i][k];
                        path[i][j] = k;
                    }
                }
            }
        }

        System.out.println(dp[M][N]);
        int cur = N;
        StringBuilder sb = new StringBuilder();
        for (int i = M; i >=1; i--){
            sb.insert(0, path[i][cur] + " ");
            cur -= path[i][cur];
        }
        System.out.println(sb.toString());
    }
}
