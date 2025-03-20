import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    static int max = 0, maxV;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        visited = new boolean[V+1];

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            while(true){
                int a = Integer.parseInt(st.nextToken());
                if(a==-1)break;
                int b = Integer.parseInt(st.nextToken());

                graph.get(idx).add(new int[]{a,b});
            }
        }
        visited[1] = true;
        dfs(1,0);
        visited = new boolean[V+1];
        max = 0;
        visited[maxV] = true;
        dfs(maxV,0);
        System.out.println(max);
    }

    static void dfs(int v, int dist){
        if (dist >= max){
            max = dist;
            maxV = v;
        }
        for (int[] nv : graph.get(v)){
            if (visited[nv[0]])continue;
            visited[nv[0]] = true;
            dist += nv[1];
            dfs(nv[0],dist);
            dist -= nv[1];
            visited[nv[0]] = false;
        }
    }
}
