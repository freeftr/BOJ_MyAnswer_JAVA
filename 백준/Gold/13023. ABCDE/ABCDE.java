import java.io.*;
import java.util.*;
public class Main {
    public static boolean[] visited;
    public static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        visited = new boolean[N];
        for(int i = 0; i < N; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for(int i = 0; i < N; i++){
            if(result == 0){
                dfs(graph, i,1);
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dfs(ArrayList<ArrayList<Integer>> graph, int start, int depth){
        if(depth == 5){
            result = 1;
            return;
        }
        visited[start] = true;
        for(int i : graph.get(start)){
            int next = i;
            if(!visited[i]){
                dfs(graph, next, depth+1);
            }
        }
        visited[start] = false;
    }
}