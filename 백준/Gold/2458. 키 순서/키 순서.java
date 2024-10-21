import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Integer>> rgraph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        rgraph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            rgraph.get(b).add(a);
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if(bfs(i,graph) + bfs(i,rgraph) == N+1){
                ans++;
            }
        }
        System.out.println(ans);
    }

    static int bfs(int start, ArrayList<ArrayList<Integer>> g){
        visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        int cnt = 0;
        while(!q.isEmpty()){
            int v = q.poll();
            cnt++;
            for (int nv : g.get(v)) {
                if(visited[nv]) continue;
                visited[nv] = true;
                q.add(nv);
            }
        }

        return cnt;
    }
}