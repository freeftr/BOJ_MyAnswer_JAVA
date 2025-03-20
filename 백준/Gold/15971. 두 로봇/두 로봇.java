import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int robotA = Integer.parseInt(st.nextToken());
        int robotB = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, cost});
            graph.get(b).add(new int[] {a, cost});
        }

        dfs(robotA, robotB, 0, 0, new boolean[N + 1]);

        System.out.println(ans);
    }

    static void dfs(int v, int robotB, int dist, int max, boolean[] visited) {
        if (v == robotB) {
            ans = Math.min(ans, dist - max);
            return;
        }

        visited[v] = true;

        for (int[] edge : graph.get(v)) {
            int nv = edge[0];
            int weight = edge[1];

            if (visited[nv]) continue;

            int tempMax = max;
            dist += weight;
            max = Math.max(max, weight);

            dfs(nv, robotB, dist, max, visited);

            dist -= weight;
            max = tempMax;
        }

        visited[v] = false;
    }
}
