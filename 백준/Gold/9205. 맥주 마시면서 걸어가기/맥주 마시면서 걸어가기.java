import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int total = n + 2;

            HashMap<Integer, int[]> map = new HashMap<>();
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int j = 0; j < total; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < total; j++) {
                st = new StringTokenizer(br.readLine());
                map.put(j, new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            }

            for (int j = 0; j < total; j++) {
                for (int k = j + 1; k < total; k++) {
                    if (distance(map.get(j), map.get(k)) <= 1000) {
                        graph.get(j).add(k);
                        graph.get(k).add(j);
                    }
                }
            }

            if (bfs(graph, total)) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }
        System.out.println(sb);
    }

    static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    static boolean bfs(ArrayList<ArrayList<Integer>> graph, int total) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[total];
        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == total - 1) {
                return true;
            }
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}
