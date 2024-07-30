import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1);
        bw.write(depth-1+"");
        bw.close();
    }
    static int depth=0;
    static void dfs(int start){
        visited[start] = true;
        depth++;
        for(int i : graph.get(start)){
            int next = i;
            if(!visited[i]){
                dfs(next);
            }
        }
    }
}
