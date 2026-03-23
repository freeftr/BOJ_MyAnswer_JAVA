import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] parent = new int[N + 1];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        q.add(new int[]{1, 1});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];

            for (int nv : graph.get(v)) {
                if (visited[nv]) continue;
                q.add(new int[]{nv});
                parent[nv] = v;
                visited[nv] = true;
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }
}
