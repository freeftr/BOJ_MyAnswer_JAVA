import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] candy;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        candy = new int[N+1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                list.add(bfs(i));
            }
        }

        int[] dp = new int[K + 1];
        for (int[] group : list) {
            int groupSize = group[0];
            int candySum = group[1];

            for (int j = K; j >= groupSize; j--) {
                dp[j] = Math.max(dp[j], dp[j - groupSize] + candySum);
            }
        }

        System.out.println(dp[K-1]);
    }

    static int[] bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int[] result = new int[2];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result[0]++;
            result[1] += candy[cur];
            for (int nv : graph.get(cur)) {
                if (!visited[nv]) {
                    queue.add(nv);
                    visited[nv] = true;
                }
            }
        }
        return result;
    }
}