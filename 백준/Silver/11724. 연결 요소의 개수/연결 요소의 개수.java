import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            if(!visited[i]){
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
;    }
    public static void dfs(int v){
        for(int i : graph.get(v)){
            if(!visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }
        return;
    }
}
