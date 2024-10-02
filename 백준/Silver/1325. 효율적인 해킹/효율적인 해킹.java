import java.io.*;
import java.util.*;
public class Main {

    static int N, M;
    static int[] dist;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(B).add(A);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            visited[i] = true;
            dfs(i,i);
            max = Math.max(max, dist[i]);
        }

        for (int i = 1; i <= N; i++) {
            if(dist[i]==max){
                sb.append(i + " ");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int v, int start){
        for(int nv : graph.get(v)){
            if(visited[nv])continue;
            visited[nv] = true;
            dist[start]++;
            dfs(nv, start);
        }
    }
}