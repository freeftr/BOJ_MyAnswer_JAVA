import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    static ArrayList<Integer> team1 = new ArrayList<>();
    static ArrayList<Integer> team2 = new ArrayList<>();

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N-2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                if(check){
                    bfs(i,team1);
                }
                else{
                    check = true;
                    bfs(i,team2);
                }
            }
        }

        System.out.println(team1.get(0) + " " + team2.get(0));
    }

    static void bfs(int v, ArrayList<Integer> set) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            set.add(cur);
            for(int nv : graph.get(cur)){
                if(!visited[nv]){
                    visited[nv] = true;
                    q.add(nv);
                }
            }
        }
    }
}