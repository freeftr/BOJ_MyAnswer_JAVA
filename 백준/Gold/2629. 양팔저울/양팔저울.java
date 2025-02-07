import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[][] dp = new boolean[31][30*500+1];
    static int[] weights;
    static int[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weights = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        M = Integer.parseInt(br.readLine());
        balls = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            balls[i]= Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            if (balls[i] > 15000) {
                sb.append("N ");
                continue;
            }

            if (dp[N][balls[i]]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth, int weight){
        if (depth == N) {
            dp[N][weight] = true;
            return;
        }

        if (dp[depth][weight]){
            return;
        }
        dp[depth][weight] = true;
        // 선택 안함
        dfs(depth+1, weight);
        // 선택 함 +
        dfs(depth+1, weight+weights[depth]);
        // 선택 함 -
        dfs(depth+1, Math.abs(weight-weights[depth]));
    }
}
