import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        //dp[s][e] = s부터 e까지 팰린드롬인지 확인
        dp = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        for (int length = 2; length <= N; length++) { 
            for (int i = 0; i <= N - length; i++) {
                int j = i + length - 1; 
                if (arr[i] == arr[j]) {
                    if (length == 2) {
                        dp[i][j] = true;
                    } else { 
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;

            if (dp[S][E]) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        System.out.print(sb);
    }
}