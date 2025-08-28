import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, N;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    static int branchLength = 0;
    static int bodyLength = -1;
    static int giga;
    static int parentOfGiga = -1;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, d});
            graph.get(b).add(new int[]{a, d});
        }

        findGiga(R, -1, 0);
        findMaxBranch(giga, parentOfGiga);

        System.out.println(bodyLength + " " + branchLength);
    }

    static void findGiga(int v, int parent, int dist) {
        int deg = 0;
        int nextV = -1, nextD = 0;

        for (int[] e : graph.get(v)) {
            if (e[0] == parent) continue;
            deg++;
            nextV = e[0];
            nextD = e[1];
        }

        if (deg == 0 || deg >= 2) {
            giga = v;
            parentOfGiga = parent;
            bodyLength = dist;
            return;
        }

        findGiga(nextV, v, dist + nextD);
    }

    static void findMaxBranch(int start, int parent) {
        Queue<int[]> q = new ArrayDeque<>();
        if (parent != -1) visited[parent] = true;
        visited[start] = true;
        q.add(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], d = cur[1];
            branchLength = Math.max(branchLength, d);

            for (int[] n : graph.get(x)) {
                int nv = n[0], nd = n[1];
                if (visited[nv]) continue;
                visited[nv] = true;
                q.add(new int[]{nv, d + nd});
            }
        }
    }
}
