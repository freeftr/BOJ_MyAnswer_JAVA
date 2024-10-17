import java.io.*;
import java.util.*;

public class Main {

    static int N, P, K;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int a;
        int cost;
        public Edge(int a, int cost) {
            this.a = a;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int left = 0;
        int right = 0;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, cost));
            graph.get(b).add(new Edge(a, cost));
            right = Math.max(right, cost);
        }

        int ans = -1;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (check(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    static boolean check(int mid) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        int[] cnt = new int[N + 1];
        Arrays.fill(cnt, Integer.MAX_VALUE);
        cnt[1] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int v = cur.a;
            int cost = cur.cost;

            if (visited[v]) continue;
            visited[v] = true;

            for (Edge nv : graph.get(v)) {
                int next = nv.a;
                int nextCost = cost;
                if (nv.cost > mid) {
                    nextCost += 1;
                }


                if (nextCost < cnt[next]) {
                    cnt[next] = nextCost;
                    pq.add(new Edge(next, cnt[next]));
                }
            }
        }
        if (cnt[N] <= K) {
            return true;
        }
        return false;
    }
}