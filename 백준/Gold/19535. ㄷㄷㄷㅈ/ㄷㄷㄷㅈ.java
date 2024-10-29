import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] inDeg;
    static long d = 0, g = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inDeg = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
            inDeg[a]++;
            inDeg[b]++;
        }

        bfs(1);

        if (d > 3 * g) {
            System.out.println("D");
        } else if (d < 3 * g) {
            System.out.println("G");
        } else {
            System.out.println("DUDUDUNGA");
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (inDeg[v] >= 3) {
                g += (long) inDeg[v] * (inDeg[v] - 1) * (inDeg[v] - 2) / 6;
            }

            for (int nv : graph.get(v)) {
                if (!visited[nv]) {
                    d += (long) (inDeg[nv] - 1) * (inDeg[v] - 1);
                    visited[nv] = true;
                    queue.add(nv);
                }
            }
        }
    }
}