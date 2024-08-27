import java.io.*;
import java.util.*;
public class Main {

    static int N, R, Q;
    static int U, V;
    static ArrayList<Integer> roots = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        dp = new int[100001];
        Arrays.fill(dp,1);

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }

        for(int i = 0; i < Q; i++){
            int a = Integer.parseInt(br.readLine());
            roots.add(a);
        }

        dfs(R);

        for(int i = 0; i < Q; i++){
            System.out.println(dp[roots.get(i)]);
        }

    }

    public static int dfs(int v){
        if(visited[v]) return dp[v];

        visited[v] = true;

        for(int nv : graph.get(v)){
            if(!visited[nv]){
                dp[v] += dfs(nv);
            }
        }

        return dp[v];
    }
}