import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] color = new int[V + 1];
            boolean bipartite = true;

            Queue<Integer> q = new ArrayDeque<>();

            for (int start = 1; start <= V; start++) {
                if (color[start] != 0) continue;

                q.add(start);
                color[start] = 1;

                while (!q.isEmpty() && bipartite) {
                    int v = q.poll();

                    for (int nv : graph.get(v)) {
                        if (color[nv] == 0) {
                            color[nv] = 3 - color[v];
                            q.add(nv);
                        } else {
                            if (color[nv] == color[v]) {
                                bipartite = false;
                                break;
                            }
                        }
                    }
                }

                if (!bipartite) break;
            }

            sb.append(bipartite ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}
