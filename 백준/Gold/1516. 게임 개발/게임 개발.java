import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] inDeg;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] time;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        inDeg = new int[N+1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            time[i] = t;

            while (true) {
                int a = Integer.parseInt(st.nextToken());
                if (a == -1) break;

                inDeg[i]++;
                graph.get(a).add(i);
            }
        }

        topologicalSort();

        System.out.println(sb);
    }

    static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                inDeg[next]--;
                dp[next] = Math.max(dp[next], dp[cur] + time[cur]);

                if (inDeg[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append((dp[i] + time[i]) + "\n");
        }
    }
}
