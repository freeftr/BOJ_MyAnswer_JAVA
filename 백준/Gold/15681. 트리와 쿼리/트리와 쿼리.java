import java.io.*;
import java.util.*;

public class Main {

    static int N, R, Q;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1];

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(dp[U]).append('\n');
        }

        System.out.print(sb.toString());
    }

    static int dfs(int v) {
        visited[v] = true;
        dp[v] = 1;

        for (int nv : graph.get(v)) {
            if (!visited[nv]) {
                dp[v] += dfs(nv);
            }
        }
        return dp[v];
    }
}
