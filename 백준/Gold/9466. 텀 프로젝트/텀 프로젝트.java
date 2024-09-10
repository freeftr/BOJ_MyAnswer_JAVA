import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[] visited;
    static boolean[] check;
    static ArrayList<ArrayList<Integer>> graph;
    static int cnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            cnt = 0;
            visited = new boolean[N+1];
            check = new boolean[N+1];

            graph = new ArrayList<>();
            for(int i = 0; i<= N; i++){
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<= N; i++){
                int to = Integer.parseInt(st.nextToken());
                graph.get(i).add(to);
            }

            for(int i = 1; i <= N; i++){
                if(!check[i]) dfs(i);
            }
            System.out.println(N-cnt);
        }
    }

    public static void dfs(int v){
        if (visited[v]){
            check[v] = true;
            cnt++;
        }
        else{
            visited[v] = true;
        }

        if(!check[graph.get(v).get(0)]){
            dfs(graph.get(v).get(0));
        }

        visited[v] = false;
        check[v] = true;
    }
}