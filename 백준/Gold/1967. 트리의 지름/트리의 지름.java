import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    static int max = 0;
    static int end = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(root).add(new int[]{child, cost});
            graph.get(child).add(new int[]{root, cost});
        }

        visited = new boolean[N+1];
        dfs(1, 0);

        visited = new boolean[N+1];
        dfs(end, 0);

        System.out.println(max);
    }

    static void dfs(int v, int dist) {
        visited[v] = true;

        if (dist > max) {
            max = dist;
            end = v;
        }

        for (int[] next : graph.get(v)) {
            int nv = next[0];
            int cost = next[1];
            if (!visited[nv]) {
                dfs(nv, dist + cost);
            }
        }
    }
}