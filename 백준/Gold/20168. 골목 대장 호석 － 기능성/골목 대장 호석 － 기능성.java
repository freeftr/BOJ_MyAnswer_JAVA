import java.io.*;
import java.util.*;

public class Main {

    static int N, M, A, B, C;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        visited[A] = true;
        dfs(A, B, 0,0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static void dfs(int v, int target, int maxCost, int sum) {
        if (sum > C) return;

        if (v==target) {
            answer = Math.min(answer, maxCost);
            return;
        }

        for (int[] nv : graph.get(v)) {
            if (visited[nv[0]]) continue;
            visited[nv[0]] = true;
            maxCost = Math.max(maxCost, nv[1]);
            sum+=nv[1];
            dfs(nv[0], target, maxCost, sum);
            sum-=nv[1];
            visited[nv[0]] = false;
        }
    }

}
