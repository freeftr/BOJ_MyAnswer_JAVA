import java.io.*;
import java.util.*;

public class Main {
    static int N, E, v1, v2;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static public class Node implements Comparable<Node> {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N 경로 계산
        int one = safeAdd(safeAdd(dijkstra(1, v1), dijkstra(v1, v2)), dijkstra(v2, N));
        // 1 -> v2 -> v1 -> N 경로 계산
        int two = safeAdd(safeAdd(dijkstra(1, v2), dijkstra(v2, v1)), dijkstra(v1, N));

        // 둘 다 경로가 없을 때
        if (one >= Integer.MAX_VALUE && two >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(one, two));
        }
    }

    public static int dijkstra(int start, int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.v] < now.cost) {
                continue;
            }

            for (Node next : graph.get(now.v)) {
                if (dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = dist[now.v] + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist[target];
    }

    public static int safeAdd(int a, int b) {
        // 둘 중 하나라도 Integer.MAX_VALUE이면 경로가 없다는 의미이므로 Integer.MAX_VALUE 반환
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return a + b;
    }
}