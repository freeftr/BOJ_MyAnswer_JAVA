import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 자신과 연결된 다른 사람들이 모두 얼아 면 아이디어 흡수
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        dp = new int[N+1][2];


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    public static void dfs(int v){
        visited[v] = true;
        dp[v][0] = 0;
        dp[v][1] = 1;
        for(int nv: graph.get(v)){
            if(visited[nv])continue;
            dfs(nv);
            dp[v][0] += dp[nv][1];
            dp[v][1] += Math.min(dp[nv][0], dp[nv][1]);
        }
    }
}