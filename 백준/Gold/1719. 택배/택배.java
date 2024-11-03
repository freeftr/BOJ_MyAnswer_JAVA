import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] dist;
    static int[] path;
    static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        ans = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        for (int i = 1; i <= n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            pq.offer(new int[]{i, 0});
            visited = new boolean[n+1];
            dist = new int[n+1];
            path = new int[n+1];

            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if(visited[cur[0]]) continue;
                visited[cur[0]] = true;

                for(int[] next : graph.get(cur[0])) {
                    if(dist[next[0]] > dist[cur[0]] + next[1]) {
                        path[next[0]] = cur[0];
                        dist[next[0]] = dist[cur[0]] + next[1];
                        pq.offer(new int[]{next[0], dist[next[0]]});
                    }
                }
            }

            for (int j = 1; j <= n; j++) {
                if(i == j){
                    sb.append("- ");
                } else {
                    sb.append(find(j, i) + " ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int find(int v, int start) {
        if(path[v] == start) {
            return v;
        }
        if(path[v] == 0) {
            return -1;
        }
        return find(path[v], start);
    }
}