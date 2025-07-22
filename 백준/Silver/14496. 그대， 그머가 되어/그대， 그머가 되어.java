import java.io.*;
import java.util.*;

public class Main {

    static int a, b, N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, 0});
        visited[a] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int dist = cur[1];

            if (v == b) {
                return dist;
            }

            for (int nv : graph.get(v)) {
                if (visited[nv]) continue;
                q.add(new int[]{nv, dist+1});
                visited[nv] = true;
            }
        }

        return -1;
    }
}
